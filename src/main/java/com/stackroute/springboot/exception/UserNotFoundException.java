package com.stackroute.springboot.exception;

public class UserNotFoundException extends Throwable {
    private String message;

    public UserNotFoundException(){

    }
    public UserNotFoundException(String message){
        super();
        this.message=message;
    }

}
