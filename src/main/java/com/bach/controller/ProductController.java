package com.bach.controller;

import com.bach.dao.product.ProductDAO;
import com.bach.model.product.Product;
import com.bach.service.ProductService;

import java.util.List;

public class ProductController implements ProductService {
    private ProductDAO dao = new ProductDAO();

    @Override
    public void add(Product p) {
        dao.insert(p);
    }

    @Override
    public void update(Product p) {
        dao.update(p);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public List<Product> getAll() {
        return dao.findAll();
    }
}
