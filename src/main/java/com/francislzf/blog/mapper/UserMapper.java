package com.francislzf.blog.mapper;


import com.francislzf.blog.bean.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface UserMapper extends Mapper<User> {
    User getUserByUsername(@Param("username") String username);
}
