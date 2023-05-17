package com.stackroute.springboot.exception;

public class Exception extends Throwable{


    private String message;

    public Exception() {
    }

    public Exception(String message) {
        this.message = message;
    }
}
