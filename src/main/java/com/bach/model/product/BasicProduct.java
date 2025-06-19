package com.bach.model.product;

public class BasicProduct extends Product {

    public BasicProduct( int supplierId, String name, String description, double price, String state, int adminId) {
        super( supplierId, name, description, price, state, adminId);
    }

    public BasicProduct(int id ,  int supplierId, String name, String description, double price, String state, int adminId) {
        super(id ,  supplierId, name, description, price, state, adminId);
    }
}
