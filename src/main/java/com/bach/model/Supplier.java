package com.bach.model;

import com.bach.patterns.sessionsingleton.Session;

public class Supplier {

    private int id;
    private String name;
    private String phone;
    private String address;
    private String email;
    private int adminId;

    public Supplier() {
        this.setAdminId(Session.getInstance().getId());
    }

    public Supplier(int id, String name, String phone, String address, String email, int adminId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.adminId = adminId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
