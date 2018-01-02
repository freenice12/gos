package com.example.demo.commons.errors;

public class ErrorResponse {
    private String message;
    private String code;

    public ErrorResponse() {
        this.message = "No specific message";
        this.code = "???";
    }

    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
