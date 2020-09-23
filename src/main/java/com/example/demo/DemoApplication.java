package com.example.demo;

import com.example.demo.DAO.CustomerDAO;
import com.example.demo.DAO.JdbcCustomerDAO;
import com.sun.xml.bind.annotation.OverrideAnnotationOf;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;

@ImportResource
		("classpath:/beans.xml")

@PropertySource
		(value = "classpath:application.properties", encoding = "utf-8")

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
private SingleRoute routesBuilder;
private CamelContext context;
private JdbcTemplate template;
private CustomerDAO customerDAO;

	@Autowired
	public DemoApplication(SingleRoute routesBuilder, CamelContext context, JdbcTemplate template, @Qualifier("namedParameterJdbcCustomerDAO") CustomerDAO customerDAO) {
		this.routesBuilder = routesBuilder;
		this.context = context;
		this.template = template;
		this.customerDAO = customerDAO;
	}


	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		TableSetUp table = new TableSetUp(template, customerDAO);
		table.runJDBC();
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
