package com.example.demo.DAO;

import com.example.demo.CustomersAndUsers.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {

    int count();

    int insert(Customer customer);

    Optional<Customer> findById(int id);

    int update(Customer customer);

    int deleteById(int id);
}
