package com.example.demo.Order;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.springframework.stereotype.Component;

@Component
public class IsOrder implements Predicate {
    @Override
    public boolean matches(Exchange exchange) {
        return exchange.getIn().getHeader("order").equals(true);
    }
}
