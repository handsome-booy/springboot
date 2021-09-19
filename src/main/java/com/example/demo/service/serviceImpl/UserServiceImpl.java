package com.example.demo.service.serviceImpl;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User login(String username,String password) {
        return userMapper.login(username,password);
    }

    @Override
    public int register(String username,String password,String question,String answer) {
        return userMapper.register(username,password,question,answer);
    }
}
