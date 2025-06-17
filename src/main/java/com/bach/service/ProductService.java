package com.bach.service;

import com.bach.model.Product;
import com.bach.dao.product.ProductDAO;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAO();

    public List<Product> getAll() {
        return productDAO.getAllProducts();
    }

    public void updatePrice(int id, float price) {
        productDAO.updatePrice(id, price);
    }
}
