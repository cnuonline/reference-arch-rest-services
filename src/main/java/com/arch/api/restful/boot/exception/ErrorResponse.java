package com.arch.api.restful.boot.exception;

/**
 * Created by SRINIVASULU on 31/10/16.
 */
public class ErrorResponse {
    private int errorCode;
    private String message;

    public int getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
