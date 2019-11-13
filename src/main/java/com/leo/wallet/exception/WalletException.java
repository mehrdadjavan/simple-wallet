package com.leo.wallet.exception;


import com.leo.wallet.model.CodeConstant;

public class WalletException extends Exception {

    private Exception nested = null;
    private int messageCode = CodeConstant.INTERNAL_ERROR;
    private String message = "";
    private String messageState = "";
    public static final byte LAYER = 3;

    public WalletException() {
        super();
    }

    public WalletException(Exception nested) {
        super(nested);
        this.message = nested.getMessage();
        this.nested = nested;
        this.messageCode = CodeConstant.INTERNAL_ERROR;

    }

    public WalletException(int messageCode) {
        super();
        this.messageCode = (messageCode == 0 ? CodeConstant.INTERNAL_ERROR : messageCode);
    }

    public WalletException(int messageCode, String message) {
        super();
        this.messageCode = (messageCode == 0 ? CodeConstant.INTERNAL_ERROR : messageCode);
        this.message = message;
    }

    public WalletException(int messageCode, String message, String messageState) {
        super();
        this.messageCode = (messageCode == 0 ? CodeConstant.INTERNAL_ERROR : messageCode);
        this.message = message;
        this.messageState = messageState;
    }


    public int getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(int messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageState() {
        return messageState;
    }

    public void setMessageState(String messageState) {
        this.messageState = messageState;
    }

    public Exception getNested() {
        return nested;
    }

}
