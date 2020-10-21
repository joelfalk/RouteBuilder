package com.example.demo.Routes;


import com.example.demo.Repository.*;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.example.demo.Config.ENDPOINT_GREETING;
import static com.example.demo.Config.ENDPOINT_NEW_USER;

@Service
public class NewUserRoute extends RouteBuilder {
    private final CustomerRepository customerRepository;


    @Autowired
    public NewUserRoute(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void configure() throws Exception {
                from(ENDPOINT_NEW_USER)
                        .bean(customerRepository, "add")
                        .process(exchange -> log.info("Customer headers: "
                                + exchange.getIn().getHeaders()))
                .to(ENDPOINT_GREETING);
    }
}
