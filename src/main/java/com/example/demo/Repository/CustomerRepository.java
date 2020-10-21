package com.example.demo.Repository;

import com.example.demo.CustomersAndUsers.Customer;
import com.example.demo.DAO.CustomerDAO;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepository {
    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerRepository(@Qualifier("namedParameterJdbcCustomerDAO") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Customer add(Exchange exchange) {
        Object o = exchange.getIn().getBody();
        if (o instanceof Customer) {
            Customer customer = new Customer(
                    ((Customer) o).getName(),
                    ((Customer) o).getId(),
                    ((Customer) o).getBank(),
                    ("Basic"));
            customerDAO.insert(customer);
            exchange.getIn().setHeader("permission", "Basic");
            return customer;
        }
        return null;
    }
}
