package com.stackroute.springboot.exception;

public class UserAlreadyExistsException extends Throwable {
    private String message;

    public UserAlreadyExistsException(){

    }

    public UserAlreadyExistsException(String message){
        super();
        this.message=message;
    }
}
