package com.example.demo.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommonServerResponse{
    int code;
    String message;

    private CommonServerResponse(int code, String message){
        this.code = code;
        this.message = message;
    }


    public static CommonServerResponse setResponse(int code, String message){
        return new CommonServerResponse(code,message);
    }
}
