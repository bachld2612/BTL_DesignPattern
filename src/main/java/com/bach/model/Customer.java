package com.bach.model;

import java.time.LocalDate;
import java.util.Date;

public class Customer extends Admin{

    private String address;
    private Date dateOfBirth;

    public Customer(String address, Date dateOfBirth) {
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
    public Customer(int id, String fullName, String phone) {
        super(id, null, null, fullName, phone); // username và password tạm để null
    }
    public Customer(int id, String username, String password, String fullName, String phone, String address, Date dateOfBirth) {
        super(id, username, password, fullName, phone);
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public Customer() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}