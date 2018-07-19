package cn.yang.service;

import cn.yang.domain.User;

public interface UserService {
    User getUserByCodePassword(User user);

    void regist(User user);

}
