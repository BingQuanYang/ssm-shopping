package com.smart.shopping.utils;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public enum ErrorStatus {
    ERROR(404,"网络异常"),
    SYS_ERROR(4000,"系统异常"),
    SERVICE_ERROR(4001,"服务异常");
    private int status;
    private String message;

    ErrorStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
