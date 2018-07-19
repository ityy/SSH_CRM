package cn.yang.web.action;

import cn.yang.domain.BaseDict;
import cn.yang.service.BaseDictService;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.util.List;

public class BaseDictAction extends ActionSupport {
    //属性驱动接收参数
    private String dict_type_code;
    //等待spring注入
    private BaseDictService baseDictService;
    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }

    //覆写默认方法
    //为了ajax查询 所以无返回值 只有将json写入response
    @Override
    public String execute() throws Exception {
        List<BaseDict> list = baseDictService.getListByTypeCode(dict_type_code);
        String gson = new Gson().toJson(list);
        ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(gson);
        return null;
    }


    public String getDict_type_code() {
        return dict_type_code;
    }

    public void setDict_type_code(String dict_type_code) {
        this.dict_type_code = dict_type_code;
    }
}
