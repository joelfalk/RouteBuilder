package com.example.demo.Greetings;

import com.example.demo.Customer;
import org.springframework.stereotype.Component;

@Component
public class PremiumGreeting {

    public String premiumGreeting(Customer customer) {

        return String.format("Hello Premium User %s, this is a Premium Greeting for a Premium person. Premium!", customer.getName());
    }
}
