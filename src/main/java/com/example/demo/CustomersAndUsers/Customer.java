package com.example.demo.CustomersAndUsers;

import org.apache.camel.Pattern;

import javax.validation.constraints.Max;
import java.util.Objects;


public class Customer {
    private int id;
    private String name;
    private String bank;
    private String permission;



    public Customer(String name, int id, String bank, String permission) {
        this.name = name;
        this.id = id;
        this.bank = bank;
        this.permission = permission;

    }



    public void setId(int id) {
        this.id = id;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public  void setBank(String bank) {
        this.bank = bank;
    }

    public void setPermission(String permission) {
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
