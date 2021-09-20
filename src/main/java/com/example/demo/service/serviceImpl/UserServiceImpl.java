package com.example.demo.service.serviceImpl;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
    UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        String pass = MD5Util.inputPassToDBPass(password,"salt");
        return userMapper.login(username,pass);
    }

    @Override
    public int register(User user) {
        String pass = MD5Util.formPassToDBPass(user.getPassword(),"salt");
        user.setPassword(pass);
        return userMapper.register(user);
    }

    @Override
    public String forget(String username) {
        return userMapper.forget(username);
    }

    @Override
    public int updatePassword(String username, String newPassword) {
        return userMapper.updatePassword(username,newPassword);
    }
}
