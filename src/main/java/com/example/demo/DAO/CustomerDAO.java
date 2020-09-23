package com.example.demo.DAO;

import com.example.demo.Customer;

import java.util.Optional;

public interface CustomerDAO {

    int insert(Customer customer);

    Optional<Customer> findById(int id);

    int update(Customer customer);

    int deleteById(int id);
}
