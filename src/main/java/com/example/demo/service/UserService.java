package com.example.demo.service;


import com.example.demo.pojo.User;

public interface UserService {
    User login(String username, String password);

    int register(User user);

    User checkedUser(String username);

    String forget(String username);

    int updatePassword(String username, String newPassword);

}
