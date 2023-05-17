package com.stackroute.springboot.exception;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value="${data.exception.message1}")
    private String message1;

    @Value(value="${data.exception.message3}")
    private String message3;

    @Value(value="${data.exception.message4}")
    private String message4;

    @ExceptionHandler(value=UserAlreadyExistsException.class)
    public ResponseEntity<String> userAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException){
        return new ResponseEntity<String>(message1, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value=UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException userNotFoundException){
        return new ResponseEntity<String>(message3,HttpStatus.NOT_FOUND);
    }


//    @ExceptionHandler(value=Exception.class)
//    public ResponseEntity<String> databaseConnectionFailsException(Exception exception) {
////        return new ResponseEntity<>(message4, HttpStatus.INTERNAL_SERVER_ERROR);
//    }




    @ExceptionHandler(value = Exception.class)
   public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
       return new ResponseEntity<>(message4, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
