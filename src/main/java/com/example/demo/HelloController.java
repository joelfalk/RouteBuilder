package com.example.demo;


import org.apache.camel.CamelContext;
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
	public String index(@RequestBody String string) {
		ProducerTemplate producerTemplate = context.createProducerTemplate();
		String response = (String) producerTemplate.sendBody(SingleRoute.ENDPOINT_URI, ExchangePattern.InOut, string);
		return response;
	}

}





