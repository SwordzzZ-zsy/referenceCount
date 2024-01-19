package com.learn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.mapper.UserMapper;
import com.learn.pojo.User;
import com.learn.service.UserService;
import com.learn.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        User u= userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
            //加密
            String md5String=Md5Util.getMD5String(password);
            //添加
            userMapper.add(username,md5String);
    }
}
