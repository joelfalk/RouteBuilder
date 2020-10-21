package com.example.demo.ErrorHandler.ErrorMessage;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.springframework.stereotype.Component;

@Component
public class IsNameError implements Predicate {

    @Override
    public boolean matches(Exchange exchange) {
        return exchange.getIn().getHeader("FailReason").equals(1);
    }
}
