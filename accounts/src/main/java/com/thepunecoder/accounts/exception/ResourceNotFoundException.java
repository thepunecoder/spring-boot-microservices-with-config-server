package com.thepunecoder.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with the given input data %s:'%s'", resourceName, fieldName, fieldValue));
    }

    //explain this code
    // This code defines a custom exception class named ResourceNotFoundException that extends the RuntimeException class.
    // It is annotated with @ResponseStatus to indicate that when this exception is thrown, it should result in an HTTP 404 Not Found response.
    //explain about string.format
    // The constructor of the class takes three parameters: resourceName, fieldName, and fieldValue.
    // It uses String.format to create a formatted error message that includes the resource name, field name, and field value.

}
