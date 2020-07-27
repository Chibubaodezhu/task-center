package com.huangjiang.taskcenter.common;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private long serialVersionUID = 1L;
    private String code;
    private String message;
    private String cause;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", cause='" + cause + '\'' +
                ", data=" + data +
                '}';
    }
}
