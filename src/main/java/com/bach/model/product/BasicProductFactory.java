package com.bach.model.product;

public class BasicProductFactory implements ProductFactory {
    @Override
    public Product createProduct(  int supplierId, String name, String description, double price, String state, int adminId) {
        return new BasicProduct(  supplierId, name, description, price, state, adminId);
    }
    @Override
    public Product createProduct(int id, int supplierId, String name, String description, double price, String state, int adminId) {
        return new BasicProduct(id , supplierId, name, description, price, state, adminId);
    }
}
