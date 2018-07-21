package cn.yang.service.impl;

import cn.yang.dao.UserDao;
import cn.yang.domain.User;
import cn.yang.service.UserService;
import cn.yang.utils.MD5Utils;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    @Override
    public User getUserByCodePassword(User user) {
        //1 根据登录名 判断是否存在
        User existUser = userDao.getByUserCode(user.getUser_code());
        if (existUser == null) {
            throw new RuntimeException("用户名不存在!");
        }
        //2 根据密码 判断是否正确
        if (!existUser.getUser_password().equals(MD5Utils.md5(user.getUser_password()))) {
            throw new RuntimeException("密码错误!");
        }
        //3 返回正确的用户对象
        return existUser;
    }

    @Override
    public void regist(User user) {
        //1 根据登陆名 判断是否已存在
        User existUser = userDao.getByUserCode(user.getUser_code());
        if (existUser != null) {
            throw new RuntimeException("用户名已经存在!");
        }
        //2 将密码加密 通过MD5
        user.setUser_password(MD5Utils.md5(user.getUser_password()));
        //补全数据
        user.setUser_state('1');

        //3 保存用户
        userDao.save(user);

    }

    /*
        --------------------  框架赋值_分割线  -----------------------------
     */

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
