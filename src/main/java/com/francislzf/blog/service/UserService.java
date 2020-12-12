package com.francislzf.blog.service;


import com.francislzf.blog.bean.User;

public interface UserService {
    User checkUser(String username, String password);

    User getUserById(Integer userId);
}
