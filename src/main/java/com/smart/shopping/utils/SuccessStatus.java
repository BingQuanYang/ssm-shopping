package com.smart.shopping.utils;

import lombok.Data;
import lombok.Getter;

@Getter
public enum SuccessStatus {
    SUCCESS(200,"成功");
    private int status;
    private String message;

    SuccessStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
