package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.UserDao;
import com.atguigu.springcloud.entity.User;
import com.atguigu.springcloud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ding
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Override
    public User getUser(Long id){
        User user = userDao.getUser(id);
        return user;
    }

    @Override
    public Integer addUser(User user) {
        Integer row = userDao.insertUser(user);
        return row;
    }
}
