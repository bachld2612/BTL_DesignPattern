package com.bach.service;

import java.util.List;

import com.bach.model.product.Product;

public interface ProductService {
    void add(Product p);
    void update(Product p);
    void delete(int id);
    List<Product> getAll();
}