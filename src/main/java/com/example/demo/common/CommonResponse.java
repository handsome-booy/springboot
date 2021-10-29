package com.example.demo.common;

import org.springframework.stereotype.Component;

public enum CommonResponse {
    SUCCESS(1, "success"),
    FAIL(0, "fail");

    int code;
    String message;

    private CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
