package com.bach.controller;

import com.bach.dao.product.ProductDAO;
import com.bach.model.product.Product1;
import com.bach.service.ProductService;

import java.util.List;

public class ProductController implements ProductService {
    private ProductDAO dao = new ProductDAO();

    @Override
    public void add(Product1 p) {
        dao.insert(p);
    }

    @Override
    public void update(Product1 p) {
        dao.update(p);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public List<Product1> getAll() {
        return dao.findAll();
    }
}
