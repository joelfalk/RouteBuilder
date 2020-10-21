package com.example.demo.Routes;

import com.example.demo.ErrorHandler.ErrorMessage.ErrorMessage;
import com.example.demo.ErrorHandler.ErrorMessage.IsIdError;
import com.example.demo.ErrorHandler.ErrorMessage.IsNameError;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.demo.Config.*;


@Service
public class ErrorRoute extends RouteBuilder {
    private final ErrorMessage errorMessage;
    private final IsNameError isNameError;
    private final IsIdError isIdError;
    @Autowired
    public ErrorRoute(ErrorMessage errorMessage, IsIdError isIdError, IsNameError isNameError) {
        this.errorMessage = errorMessage;
        this.isIdError = isIdError;
        this.isNameError = isNameError;
    }

    @Override
    public void configure() {
        errorHandler(deadLetterChannel("direct:dead"));

        from(ENDPOINT_ERROR)
                .choice()
                .when(isNameError)
                        .throwException(new IllegalArgumentException("noNameMatch"))
                        .endChoice()
                .when(isIdError)
                        .throwException(new IllegalArgumentException("noIdMatch"))
                        .endChoice()
                .otherwise()
                        .throwException(new IllegalArgumentException("UnknownError"))
                        .endChoice();



        from("direct:dead")
                .bean(errorMessage, "enrich")
                .to("mock:dead");

    }
}
