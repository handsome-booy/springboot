package com.example.demo.pojo;

import lombok.Data;

@Data
public class User {
    int id;
    String username;
    String password;
    String question;
    String answer;
    int manager;
}
