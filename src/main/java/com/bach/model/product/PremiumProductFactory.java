package com.bach.model.product;

public class PremiumProductFactory implements ProductFactory {
    @Override
    public Product createProduct( int supplierId, String name, String description, double price, String state, int adminId) {
        return new PremiumProduct( supplierId, name, description, price, state, adminId);
    }

    @Override
    public Product createProduct(int id,  int supplierId, String name, String description, double price, String state, int adminId) {
        return new PremiumProduct(id, supplierId, name, description, price, state, adminId);
    }
}
