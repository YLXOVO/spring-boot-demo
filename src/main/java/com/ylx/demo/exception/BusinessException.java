package com.ylx.demo.exception;


import com.ylx.demo.common.ErrorCode;

/**
 * 自定义异常类
 *
 * @author 杨乐昕
 */
public class BusinessException extends RuntimeException{
    //原本继承的异常类没有我们添加的字段code description，通过封装来添加
    private final String description;
    private final int code;

    public BusinessException(String message, String description, int code) {
        super(message);
        this.description = description;
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.description = errorCode.getDescription();
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.description = description;
        this.code = errorCode.getCode();
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }
}
