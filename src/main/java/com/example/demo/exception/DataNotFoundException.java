package com.example.demo.exception;

public class DataNotFoundException extends Exception {
    private String errorMessage;

    public DataNotFoundException(){
        super();
    }

    public DataNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
