package com.thepunecoder.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistException extends RuntimeException {

    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}

/*
 * This exception is thrown when an attempt is made to create a customer that already exists in the system.
 * It extends RuntimeException, which means it is an unchecked exception and does not require explicit handling.
 * The @ResponseStatus annotation is used to specify that when this exception is thrown, a response with a 400 Bad Request status code should be returned to the
 * client.
 * If we are adding HTTP status in @ControllerAdvice class then we can skip @ResponseStatus annotation in the exception class. But if we want to add HTTP status
 * in the exception class then we can skip adding HTTP status in @ControllerAdvice class.
 * In this case, we have added @ResponseStatus in the exception class, so we can skip adding HTTP status in @ControllerAdvice class. But if we want to add HTTP
 * status in @ControllerAdvice class then we can skip @ResponseStatus annotation in the exception class.
 * The constructor of this exception takes a message as a parameter, which can be used to provide more details about the error when the exception is thrown.
 * */