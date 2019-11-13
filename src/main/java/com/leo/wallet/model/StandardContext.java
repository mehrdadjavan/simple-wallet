package com.leo.wallet.model;


import java.io.Serializable;

public class StandardContext<T> implements Serializable {

    private int errorCode;

    private String errorMessage;

    private  T data;

    public StandardContext(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public StandardContext(int errorCode, T data) {
        this.errorCode = errorCode;
        this.data = data;
    }

    public StandardContext() {
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
