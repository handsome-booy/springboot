package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data

public class User implements Serializable {
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
