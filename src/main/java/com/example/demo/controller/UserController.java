package com.example.demo.controller;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("login")
    @ResponseBody
    public User login(String username,String password){
        return userMapper.login(username,password);
    }
}
