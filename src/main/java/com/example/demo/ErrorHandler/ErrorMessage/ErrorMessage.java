package com.example.demo.ErrorHandler.ErrorMessage;

import org.apache.camel.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ErrorMessage {

    public String enrich(@Headers Map<String, String> headers, Exception cause) throws Exception {
        String failure = "The message failed because " + cause.getMessage();
        headers.put("FailureMessage", failure);
        return failure;

    }
}
