package com.example.demo.ErrorHandler.Exceptions;


import com.example.demo.ErrorHandler.ErrorMessage.ErrorMessageHttp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomInternalServerException extends RuntimeException implements CustomExceptionProvider {

    public CustomInternalServerException(String msg) {
        super(msg);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public ErrorMessageHttp generateErrorMessage(WebRequest request) {

        ErrorMessageHttp message = new ErrorMessageHttp(
                this.getHttpStatus().value(),
                new Date(),
                this.getMessage(),
                request.getDescription(false));
        return message;
    }
}
