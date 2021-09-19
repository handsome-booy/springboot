package com.example.demo.service;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Param;


public interface UserService {
    User login(String username,String password);

    int register(String username,String password,String question,String answer);

    String forget(String username);

    int updatePassword(String username, String newPassword);

}
