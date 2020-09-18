package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Translator {

    public String translate(Customer customer) {

        return String.format("Hello %s", customer.getName());
    }
}
