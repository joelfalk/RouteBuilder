package com.example.demo.Controller;


import com.example.demo.CustomersAndUsers.Customer;
import com.example.demo.DAO.CustomerDAO;
import com.example.demo.ErrorHandler.Exceptions.CustomDBException;
import com.example.demo.Routes.InitialRoute;
import static com.example.demo.Config.*;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;



@RestController
public class GreetingController {
private final CamelContext context;
private final CustomerDAO customerDAO;

	public GreetingController(CamelContext context, @Qualifier("namedParameterJdbcCustomerDAO") CustomerDAO customerDAO) {
		this.context = context;
		this.customerDAO = customerDAO;
	}

	@PostMapping(value = "/User", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> AddCustomer(@Valid @RequestBody Customer customer, Errors errors) {

		ProducerTemplate producerTemplate = context.createProducerTemplate();
		Exchange exchange = context.getEndpoint(ENDPOINT_URI).createExchange();
		exchange.getIn().setBody(customer);
		exchange.getIn().setHeader("permission", customer.getPermission());
		exchange.getIn().setHeader("name", customer.getName());
		producerTemplate.send(ENDPOINT_URI, exchange);
		return ResponseEntity.ok((String) exchange.getProperty("response"));

	}

	@GetMapping(value = "/User/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> GetCustomer(@RequestBody Customer customer, @PathVariable("id") int id, Errors errors) {


		ProducerTemplate producerTemplate = context.createProducerTemplate();
		Exchange exchange = context.getEndpoint(ENDPOINT_URI).createExchange();
		exchange.getIn().setBody(customer);

		try {
				Customer c = customerDAO.findById(customer.getId()).orElseThrow(NullPointerException::new);

				if (c.getName().equals(customer.getName()))
					{

						exchange.getIn().setHeader("permission", c.getPermission());
						exchange.getIn().setHeader("name", c.getName());
						producerTemplate.send(ENDPOINT_URI, exchange);

						return ResponseEntity.ok((String) exchange.getProperty("response"));

					}
				else
					{
						exchange.getIn().setHeader("permission", "Unknown");
						exchange.getIn().setHeader("FailReason", 1);
						producerTemplate.send(ENDPOINT_URI,exchange);

						throw new CustomDBException(NAME_ERROR_NOT_FOUND_IN_DB);

					}
			} catch (EmptyResultDataAccessException e){

						exchange.getIn().setHeader("permission", "Unknown");
						exchange.getIn().setHeader("FailReason", 2);
						producerTemplate.send(ENDPOINT_URI,exchange);

						throw new CustomDBException(ID_ERROR_NOT_FOUND_IN_DB);

			}


	}

}





