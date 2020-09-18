package com.example.demo;


import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
private final CamelContext context;

	public HelloController(CamelContext context) {
		this.context = context;
	}

	@PostMapping(value = "/", produces = "application/json", consumes = "application/json")
	public String index(@RequestBody Customer customer) {
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		Exchange exchange = context.getEndpoint(SingleRoute.ENDPOINT_URI).createExchange();
		exchange.getIn().setBody(customer);
		exchange.getIn().setHeader("permission", customer.getPermission());
		producerTemplate.send(SingleRoute.ENDPOINT_URI, exchange);

		String response = (String) exchange.getProperty("response");
		return response;
	}

}





