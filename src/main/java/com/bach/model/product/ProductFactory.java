package com.bach.model.product;

public interface ProductFactory {
    Product createProduct( int supplierId, String name, String description, double price, String state, int adminId);
    Product createProduct(int id, int supplierId, String name, String description, double price, String state, int adminId);
}