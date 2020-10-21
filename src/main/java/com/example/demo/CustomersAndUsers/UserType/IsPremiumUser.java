package com.example.demo.CustomersAndUsers.UserType;


import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.springframework.stereotype.Component;

/*Predicate for verifying premium user*/
@Component
public class IsPremiumUser implements Predicate {


    @Override
    public boolean matches(Exchange exchange) {
        return exchange.getIn().getHeader("permission").equals("Premium");
    }
}
