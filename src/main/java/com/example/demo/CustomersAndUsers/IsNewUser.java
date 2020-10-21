package com.example.demo.CustomersAndUsers;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.springframework.stereotype.Component;

@Component
public class IsNewUser implements Predicate {
    @Override
    public boolean matches(Exchange exchange) {
        return exchange.getIn().getHeader("permission").equals("");
    }
}
