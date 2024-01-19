package com.learn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.pojo.User;

public interface UserService extends IService<User> {
    User findByUserName(String username);

    void register(String username, String password);
}
