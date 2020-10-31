package com.smart.shopping.utils;

import lombok.Builder;
import lombok.Data;

/**
 * 响应数据通用的结果集
 * @param <T>
 */
@Data
@Builder
public class ResponseEntity<T> {
    //状态码
    private int status;
    //描述状态信息
    private String message;
    //数据
    private T data;

    public static <T> ResponseEntity<T> success(T data) {
        return ResponseEntity.<T>builder()
                .status(SuccessStatus.SUCCESS.getStatus())
                .message(SuccessStatus.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public static <T> ResponseEntity<T> success(SuccessStatus successStatus, T data) {
        return ResponseEntity.<T>builder()
                .status(successStatus.getStatus())
                .message(successStatus.getMessage())
                .data(data)
                .build();
    }

    public static <T> ResponseEntity<T> error() {
        return ResponseEntity.<T>builder()
                .status(ErrorStatus.ERROR.getStatus())
                .message(ErrorStatus.ERROR.getMessage())
                .build();
    }

    public static <T> ResponseEntity<T> error(ErrorStatus errorStatus) {
        return ResponseEntity.<T>builder()
                .status(errorStatus.getStatus())
                .message(errorStatus.getMessage())
                .build();
    }
}
