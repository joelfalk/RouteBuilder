package com.example.demo;


import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.springframework.stereotype.Component;

@Component
public class StandardUser implements Predicate {

    @Override
    public boolean matches(Exchange exchange) {
        return exchange.getIn().getHeader("permission").equals("Standard");
    }
}
