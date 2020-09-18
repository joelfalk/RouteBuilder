package com.example.demo.Greetings;

import com.example.demo.Customer;
import org.springframework.stereotype.Component;

@Component
public class StandardGreeting {

    public String standardGreeting(Customer customer) {

        return String.format("Hello Standard User %s this is a standard greeting for a standard user. Standard!", customer.getName());
    }

}
