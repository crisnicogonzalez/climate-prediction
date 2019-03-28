package com.predictor.api;

import java.util.List;

public class BadRequestResponse {

    private int code;
    private String message;
    private List<String> errors;

    public BadRequestResponse() {
    }

    public BadRequestResponse(int code, String message, List<String> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
