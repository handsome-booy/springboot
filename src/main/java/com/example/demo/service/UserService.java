package com.example.demo.service;

import com.example.demo.pojo.User;


public interface UserService {
    User login(String username,String password);

    int register(String username,String password,String question,String answer);
}
