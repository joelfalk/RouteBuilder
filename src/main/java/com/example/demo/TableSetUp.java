package com.example.demo;

import com.example.demo.CustomersAndUsers.Customer;
import com.example.demo.DAO.CustomerDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;


/* Creating and populating table permissions */
public class TableSetUp {
    private JdbcTemplate template;
    private CustomerDAO customerDAO;

    public TableSetUp(JdbcTemplate template, CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
        this.template = template;
    }


    public void runJDBC() {

        System.out.println("Creating tables for testing.");

    /*    template.execute("DROP TABLE permissions");
        template.execute("CREATE TABLE permissions(" +
                "name VARCHAR(12), id Integer, bank VARCHAR(20), permission VARCHAR(20))");*/

        List<Customer> permissions = Arrays.asList(
                new Customer("Joel", 1, "Nordea", "Basic"),
                new Customer("Henrik", 2, "SWB", "Standard"),
                new Customer("Robin", 3, "SEB", "Premium"),
                new Customer("Min mamma", 4, "SHB", "Premium")
        );

        permissions.forEach(customer -> {
            System.out.print("Saving Customer to table: " + customer.getName() + "\n");
            customerDAO.insert(customer);
        });

        Customer customer = customerDAO.findById(4).orElseThrow(IllegalArgumentException::new);
        System.out.println("Customer with id=4: " + customer.getName());
    }
}
