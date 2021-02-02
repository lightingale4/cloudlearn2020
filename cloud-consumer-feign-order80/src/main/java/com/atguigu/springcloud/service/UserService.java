package com.atguigu.springcloud.service;


import com.atguigu.springcloud.entity.User;

/**
 * @author ding
 */
public interface UserService {

    User getUser(Long id);

    Integer addUser(User user);
}
