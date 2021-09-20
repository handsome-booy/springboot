package com.example.demo.service;


import com.example.demo.pojo.User;

public interface UserService {
    User login(String username, String password);

    int register(User user);

    String forget(String username);

    int updatePassword(String username, String newPassword);

}
