<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%--
        使用js、jQuery动态创建select选择框
        使用ajax异步为select赋值
        使用struts2标签取数据
        使用Action传数据
    --%>

    <%--此页面即可新增也可编辑，做适应性修改--%>
    <%--<TITLE>添加客户</TITLE>--%>
    <TITLE><s:property value="#customer==null?'添加':'修改'"/>客户</TITLE>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
    <LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
          rel=stylesheet>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>

    <SCRIPT type="text/javascript">
        <%--编写一个创建select对象的方法--%>

        function loadSelect(typecode, positionId, selectname, selectedId) {
            //1 建立空的select 利用jQuery直接写进去即可
            var $select = $("<select name=" + selectname + "></select>");
            //2 添加默认提示
            $select.append($("<option value='' >---请选择---</option>"));
            //3 ajax访问后台获取数据 ajax开发最好编写一步测试一步
            $.post(
                "${pageContext.request.contextPath}/BaseDictAction",//1，url地址
                {"dict_type_code": typecode},//2，参数
                function (data) {
                    // alert("post请求成功！");
                    // alert(data);
                    //遍历
                    $.each(data, function (i, json) {

                        // alert(i);
                        // alert(json.dict_type_name);

                        //创建option
                        var $option = $("<option value='" + json.dict_id + "'>" + json.dict_item_name + "</option>")
                        //判断是否回显
                        if (selectedId == json.dict_id) {
                            $option.attr("selected", "selected");
                        }

                        //加入select
                        $select.append($option);


                    });
                },//3，成功后的回调函数
                "json"//4，给回调函数传参的类型，有如下几种xml, html, script, json, text, _default。
            );
            //4
            //5 将组装好的select放入位置 #号是ID选择器
            $("#" + positionId).append($select);
        }

        //启动时调用
        $(function () {
            //创建select并放置到位，name为customer的外键的id
            loadSelect("006", "td_kehujibie", "cust_level.dict_id","<s:property value='#customer.cust_level.dict_id'/>");
            loadSelect("002", "td_xinxilaiyuan", "cust_source.dict_id","<s:property value='#customer.cust_source.dict_id'/>");
            loadSelect("001", "td_kehuhangye", "cust_industry.dict_id","<s:property value='#customer.cust_industry.dict_id'/>");
        });


    </SCRIPT>

    <META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
<!-- 文件上传页面3个要求:
        1.表单必须post提交
        2.表单提交类型enctype.必须多段式. enctype="multipart/form-data"
        3.文件上传使用<input type="file" /> 组件
 -->
<FORM id=form1 name=form1
      action="${pageContext.request.contextPath }/CustomerAction_add"
      method=post enctype="multipart/form-data">


    <%--添加隐藏域 用于修改时提交ID--%>
    <%--属性驱动方式传递参数--%>
    <input type="hidden" name="cust_id" value="<s:property value='#customer.cust_id'/>" >
    <TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
        <TBODY>
        <TR>
            <TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
                              border=0></TD>
            <TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
                height=20></TD>
            <TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
                              border=0></TD>
        </TR>
        </TBODY>
    </TABLE>
    <TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
        <TBODY>
        <TR>
            <TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
                    src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
            <TD vAlign=top width="100%" bgColor=#ffffff>
                <TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
                    <TR>
                        <TD class=manageHead>当前位置：客户管理 &gt; <s:property value="#customer==null?'添加':'修改'"/>客户</TD>
                    </TR>
                    <TR>
                        <TD height=2></TD>
                    </TR>
                </TABLE>

                <TABLE cellSpacing=0 cellPadding=5 border=0>


                    <TR>
                        <td>客户名称：</td>
                        <td>
                            <INPUT class=textbox id=sChannel2
                                   style="WIDTH: 180px" maxLength=50 name="cust_name" value="<s:property value='#customer.cust_name'/>">
                        </td>
                        <td>客户级别 ：</td>
                        <td id="td_kehujibie">

                        </td>
                    </TR>

                    <TR>

                        <td>信息来源 ：</td>
                        <td id="td_xinxilaiyuan">

                        </td>
                        <td>客户行业：</td>
                        <td id="td_kehuhangye">

                        </td>
                    </TR>

                    <TR>


                        <td>固定电话 ：</td>
                        <td>
                            <INPUT class=textbox id=sChannel2
                                   style="WIDTH: 180px" maxLength=50 name="cust_phone" value="<s:property value='#customer.cust_phone'/>">
                        </td>
                        <td>移动电话 ：</td>
                        <td>
                            <INPUT class=textbox id=sChannel2
                                   style="WIDTH: 180px" maxLength=50 name="cust_mobile" value="<s:property value='#customer.cust_mobile'/>">
                        </td>
                    </TR>

                    <tr>
                        <td>图片上传：</td>
                        <td>
                            <input type="file" name="photo">
                        </td>
                    </tr>

                    <tr>
                        <td rowspan=2>
                            <INPUT class=button id=sButton2 type=submit
                                   value=" 保存 " name=sButton2>
                        </td>
                    </tr>
                </TABLE>


            </TD>
            <TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
                <IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
        </TR>
        </TBODY>
    </TABLE>
    <TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
        <TBODY>
        <TR>
            <TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
                              border=0></TD>
            <TD align=middle width="100%"
                background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
            <TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
                              border=0></TD>
        </TR>
        </TBODY>
    </TABLE>


</FORM>
</BODY>
</HTML>
