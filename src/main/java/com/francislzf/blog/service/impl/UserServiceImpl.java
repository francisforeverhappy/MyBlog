package com.francislzf.blog.service.impl;

import com.francislzf.blog.bean.User;
import com.francislzf.blog.mapper.UserMapper;
import com.francislzf.blog.service.UserService;
import com.francislzf.blog.utils.MD5util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.getUserByUsername(username); //先拿到用户，再比较密码，可以避免sql注入
        return user==null?null:user.getPassword().equals(MD5util.code(password))?user:null;
    }

    @Override
    public User getUserById(Integer userId) {
        User user = new User();
        user.setId(userId);
        return userMapper.selectByPrimaryKey(user);
    }
}
