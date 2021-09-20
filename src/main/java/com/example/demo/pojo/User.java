package com.example.demo.pojo;

import lombok.Data;

@Data
public class User {
    int id;
    String name;
    String password;
    String question;
    String answer;
}
