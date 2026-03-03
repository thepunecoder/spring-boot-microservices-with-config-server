package com.thepunecoder.accounts.exception;

import com.thepunecoder.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This method is responsible for catching CustomerAlreadyExistException and returning a customized response to the client.
     * @param exception the CustomerAlreadyExistException that occurred
     * @param webRequest the web request that triggered the exception
     */
    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistException exception, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}

/*
What is @ControllerAdvice in Spring Boot?
@ControllerAdvice is a specialization of the @Component annotation in Spring Boot that allows you to handle exceptions globally across all controllers.
It is used to define a centralized exception handling mechanism, enabling you to write code that can intercept and process exceptions thrown by any controller in the application.
@ExceptionHandler is used within a @ControllerAdvice class to specify methods that handle specific exceptions.

What are the benefits of using @ControllerAdvice for exception handling?
Using @ControllerAdvice for exception handling provides several benefits:
1. Centralized Exception Handling: It allows you to manage all exception handling logic in one place, making the code cleaner and easier to maintain.
2. Reusability: You can define common exception handling logic that can be reused across multiple controllers.
3. Consistent Responses: It helps ensure that error responses are consistent throughout the application.
4. Separation of Concerns: It separates exception handling logic from business logic, improving code organization and readability.

@ControllerAdvice used for exception handling only ?
@ControllerAdvice is primarily used for exception handling in Spring Boot applications. However, it can also be used for other cross-cutting concerns such as:
1. Model Attribute Binding: You can use @ModelAttribute methods within a @ControllerAdvice class to add common attributes to the model for all controllers.
2. Data Binding: You can define @InitBinder methods to customize data binding for all controllers.
3. Global Configuration: It can be used to apply global configurations that affect multiple controllers.

*/