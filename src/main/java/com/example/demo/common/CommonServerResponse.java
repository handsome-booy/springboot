package com.example.demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonServerResponse<T> {
    int code;
    String message;
    T context;

    private CommonServerResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private CommonServerResponse(int code, String message, T context) {
        this.code = code;
        this.message = message;
        this.context = context;
    }


    public static CommonServerResponse setResponse(int code, String message) {
        return new CommonServerResponse(code, message);
    }

    public static <T> CommonServerResponse setResponse(int code, String message, T context) {
        return new CommonServerResponse(code, message, context);
    }
}
