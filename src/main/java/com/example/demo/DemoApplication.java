package com.example.demo;

import com.example.demo.DAO.CustomerDAO;
import com.example.demo.Routes.ErrorRoute;
import com.example.demo.Routes.GreetingsRoute;
import com.example.demo.Routes.InitialRoute;
import com.example.demo.Routes.NewUserRoute;
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

@ImportResource
		("classpath:/beans.xml")

@PropertySource
		(value = "classpath:application.properties", encoding = "utf-8")

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
private final InitialRoute routesBuilder;
private final CamelContext context;
private final JdbcTemplate template;
private final CustomerDAO customerDAO;
private final ErrorRoute errorRoute;
private final GreetingsRoute greetingsRoute;
private final NewUserRoute newUserRoute;


	@Autowired
	public DemoApplication(InitialRoute routesBuilder, GreetingsRoute greetingsRoute, ErrorRoute errorRoute, CamelContext context, JdbcTemplate template, @Qualifier("namedParameterJdbcCustomerDAO") CustomerDAO customerDAO, NewUserRoute newUserRoute) {
		this.routesBuilder = routesBuilder;
		this.context = context;
		this.template = template;
		this.customerDAO = customerDAO;
		this.errorRoute = errorRoute;
		this.greetingsRoute = greetingsRoute;
		this.newUserRoute = newUserRoute;
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
			context.addRoutes(errorRoute);
			context.addRoutes(greetingsRoute);
			context.addRoutes(newUserRoute);
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
