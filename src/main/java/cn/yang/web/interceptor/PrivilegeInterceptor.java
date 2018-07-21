package cn.yang.web.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 自定义拦截器
 * 当用户未登陆则拦截所有页面 跳转到登陆页面
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //1 判断session中的user是否存在
        if (ActionContext.getContext().getSession().get("user") != null) {
            //存在表示已登陆 递归放行
            return actionInvocation.invoke();
        } else {
            //不存在直接重定向到登陆页
            return "toLogin";
        }

        //2 获得登陆标识
        //3 判断是否存在

    }
}
