package com.bach.patterns.userbuilder;

import com.bach.model.Customer;

import java.time.LocalDate;
import java.util.Date;

public class CustomerBuilder implements UserBuilder{

    private Customer customer;


    @Override
    public void reset() {
        customer = new Customer();
    }

    @Override
    public void id(int id) {
        if (customer == null) {
            throw new RuntimeException("Builder not initialized. Call reset() first.");
        }
        customer.setId(id);
    }

    @Override
    public void username(String username) {
        if (customer == null) {
            throw new RuntimeException("Builder not initialized. Call reset() first.");
        }
        customer.setUsername(username);
    }

    @Override
    public void password(String password) {
        if (customer == null) {
            throw new RuntimeException("Builder not initialized. Call reset() first.");
        }
        customer.setPassword(password);
    }

    @Override
    public void fullName(String fullName) {
        if (customer == null) {
            throw new RuntimeException("Builder not initialized. Call reset() first.");
        }
        customer.setFullName(fullName);
    }

    @Override
    public void phone(String phone) {
        if (customer == null) {
            throw new RuntimeException("Builder not initialized. Call reset() first.");
        }
        customer.setPhone(phone);
    }

    @Override
    public void address(String address) {
        if (customer == null) {
            throw new RuntimeException("Builder not initialized. Call reset() first.");
        }
        customer.setAddress(address);
    }

    @Override
    public void dateOfBirth(Date dateOfBirth) {
        if (customer == null) {
            throw new RuntimeException("Builder not initialized. Call reset() first.");
        }
        customer.setDateOfBirth(dateOfBirth);
    }

    public Customer getResult() {
        if (customer == null) {
            throw new RuntimeException("Builder not initialized. Call reset() first.");
        }
        return customer;
    }
}
