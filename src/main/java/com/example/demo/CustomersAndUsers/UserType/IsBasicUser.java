package com.example.demo.CustomersAndUsers.UserType;


import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.springframework.stereotype.Component;

/*Predicate for verifying Basic user*/
@Component
public class IsBasicUser implements Predicate {

    @Override
    public boolean matches(Exchange exchange) {
        return exchange.getIn().getHeader("permission").equals("Basic");
    }
}
