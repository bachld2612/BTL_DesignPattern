package com.bach.service;

import com.bach.dao.user.AdminDAO;
import com.bach.dao.user.CustomerDAO;
import com.bach.model.Admin;
import com.bach.model.Customer;
import com.bach.patterns.sessionsingleton.Session;

public class UserService {

    private final AdminDAO adminDAO;
    private final CustomerDAO customerDAO;

    public UserService() {
        this.adminDAO = new AdminDAO();
        this.customerDAO = new CustomerDAO();
    }

    public void login(String username, String password) {
        Admin admin = adminDAO.getAdminByUsernameAndPassword(username, password);
        Customer customer = null;
        if (admin == null){
            customer = customerDAO.getCustomerByUsernameAndPassword(username, password);
            if(customer == null){
                throw new RuntimeException("Username or password is incorrect");
            }
            Session.getInstance().setUsername(customer.getUsername());
            Session.getInstance().setRole("customer");
        }else {
            Session.getInstance().setUsername(admin.getUsername());
            Session.getInstance().setRole("admin");
        }
    }

    public void register(Customer customer){
        Customer existingCustomer = customerDAO.getCustomerByUsername(customer.getUsername());
        if(existingCustomer != null){
            throw new RuntimeException("Username already exists");
        }
        customerDAO.createCustomer(customer);
    }

}
