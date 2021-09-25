package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data

public class User {
    @JsonIgnore
    int id;

    String username;

    @JsonIgnore
    String password;

    String question;

    @JsonIgnore
    String answer;

    int manager;
}
