package com.example.demo.ErrorHandler;


import com.example.demo.ErrorHandler.ErrorMessage.ErrorMessageHttp;
import com.example.demo.ErrorHandler.Exceptions.CustomDBException;
import com.example.demo.ErrorHandler.Exceptions.CustomInternalServerException;
import com.example.demo.ErrorHandler.Exceptions.CustomNotAcceptableRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {

    /* Custom exception related to Database failure. */
    @ExceptionHandler(CustomDBException.class)
    public ResponseEntity<ErrorMessageHttp> customDBException(CustomDBException ex, WebRequest request) {

        return new ResponseEntity<>(ex.generateErrorMessage(request), ex.getHttpStatus());
    }

    /* General custom made exception related to internal server errors. */
    @ExceptionHandler(CustomInternalServerException.class)
    public ResponseEntity<ErrorMessageHttp> customInternalServerException(CustomInternalServerException ex, WebRequest request) {

        return new ResponseEntity<>(ex.generateErrorMessage(request), ex.getHttpStatus());
    }

    /* Custom made exception related to conditional fail of request. */
    @ExceptionHandler(CustomNotAcceptableRequestException.class)
    public ResponseEntity<ErrorMessageHttp> customInternalServerException(CustomNotAcceptableRequestException ex, WebRequest request) {

        return new ResponseEntity<>(ex.generateErrorMessage(request), ex.getHttpStatus());
    }
}

