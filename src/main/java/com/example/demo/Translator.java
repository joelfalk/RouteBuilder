package com.example.demo;

import com.example.demo.CustomersAndUsers.Customer;
import org.springframework.stereotype.Component;

@Component
public class Translator {

    public String translate(Customer customer) {

        return String.format("Hello %s", customer.getName());
    }
}
