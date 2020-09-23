package com.example.demo;


import com.example.demo.DAO.CustomerDAO;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HelloController {
private final CamelContext context;
private final CustomerDAO customerDAO;

	public HelloController(CamelContext context, @Qualifier("namedParameterJdbcCustomerDAO") CustomerDAO customerDAO) {
		this.context = context;
		this.customerDAO = customerDAO;
	}

	@PostMapping(value = "/", produces = "application/json", consumes = "application/json")
	public String index(@RequestBody Customer customer) {
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		Exchange exchange = context.getEndpoint(SingleRoute.ENDPOINT_URI).createExchange();
		exchange.getIn().setBody(customer);
		Customer c = customerDAO.findById(customer.getId()).orElseThrow(IllegalArgumentException::new);
		exchange.getIn().setHeader("permission", c.getPermission());
		exchange.getIn().setHeader("name", c.getName());
		producerTemplate.send(SingleRoute.ENDPOINT_URI, exchange);

		String response = (String) exchange.getProperty("response");
		return response;
	}

}





