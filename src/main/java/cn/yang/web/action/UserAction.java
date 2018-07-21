package cn.yang.web.action;

import cn.yang.domain.User;
import cn.yang.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    private UserService userService;
    private User user = new User();
    private final static Logger logger = LoggerFactory.getLogger(UserAction.class);

    public String regist() throws Exception {
        logger.info("方法:regist--正在执行");

        //1 保存用户
        try {
            userService.regist(user);
        } catch (Exception e) {
            //手动创建并抓取异常,避免和配置中的异常处理冲突
            e.printStackTrace();
            ActionContext.getContext().put("error", e.getMessage());
            return "toRegist";
        }
        //2 注册完毕 重定向到登录页
        return "toLogin";
    }

    public String login() throws Exception {
        //1 登陆判断
        User u = userService.getUserByCodePassword(user);
        //2 无异常则存入session
        ActionContext.getContext().getSession().put("user", u);
        //3 重定向到首页
        return "toHome";
    }

    /*
        --------------------  框架赋值_分割线  -----------------------------
     */

    //模型驱动
    @Override
    public User getModel() {
        return user;
    }

    //为spring提供set方法
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
