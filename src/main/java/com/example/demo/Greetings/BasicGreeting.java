package com.example.demo.Greetings;

import com.example.demo.Customer;
import org.springframework.stereotype.Component;

@Component
public class BasicGreeting {

    public String basicGreeting(Customer customer) {

        return String.format("Hello Basic User %s", customer.getName());
    }
}
