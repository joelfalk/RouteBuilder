package com.example.demo;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@SpringBootApplication
public class DemoApplication {
private SingleRoute routesBuilder;
private CamelContext context;

	@Autowired
	public DemoApplication(SingleRoute routesBuilder, CamelContext context) {
		this.routesBuilder = routesBuilder;
		this.context = context;
	}


	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	private void init() {
		try {
			context.addRoutes(routesBuilder);
			context.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PreDestroy
	private void cleanUp() {
		try{
			context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
