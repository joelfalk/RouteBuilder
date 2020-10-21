package com.example.demo.ErrorHandler.Exceptions;

import com.example.demo.ErrorHandler.ErrorMessage.ErrorMessageHttp;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

public interface CustomExceptionProvider {

   default HttpStatus getHttpStatus(){
       return HttpStatus.BAD_REQUEST;
   }

    ErrorMessageHttp generateErrorMessage(WebRequest request);
}
