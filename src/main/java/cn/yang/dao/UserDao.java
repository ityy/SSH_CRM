package cn.yang.dao;

import cn.yang.domain.User;

public interface UserDao extends BaseDao<User> {
    //根据登陆名查user对象
    User getByUserCode(String usercode);


}

