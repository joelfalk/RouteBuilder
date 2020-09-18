package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Objects;


public class Customer {
    private final int id;
    private final String name;
    private final String bank;
    private final String permission;

    public Customer(String name, int id, String bank, String permission) {
        this.name = name;
        this.id = id;
        this.bank = bank;
        this.permission = permission;
    }

    public String getBank() {
        return bank;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(bank, customer.bank) &&
                Objects.equals(permission, customer.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bank, permission);
    }

    public String getName() {
        return name;
    }


    /* Basic, Standard or Premium user.*/
    public String getPermission() {
        return permission;
    }
}
