package com.bach.service;

import com.bach.dao.discount.DiscountDAO;
import com.bach.dao.product.ProductDAO;
import com.bach.model.Discount;
import com.bach.model.Product;
import com.bach.patterns.decorator.ConcreteProduct;
import com.bach.patterns.decorator.DiscountDecorator;
import com.bach.patterns.decorator.ProductComponent;

import java.util.List;

public class ProductService1 {
    private final ProductDAO productDAO = new ProductDAO();
    private final DiscountDAO discountDAO = new DiscountDAO(); // ✅ Thêm DAO khuyến mãi

    public List<Product> getAll() {
        return productDAO.getAllProducts();
    }

    // Cập nhật giá sản phẩm (chỉ dùng nếu cần)
    public void updatePrice(int id, float price) {
        productDAO.updatePrice(id, price);
    }

    public double getDiscountedPrice(Product p) {
        Discount d = discountDAO.getActiveDiscount(p.getId());
        if (d != null) {
            if ("percent".equals(d.getDiscountType())) {
                return p.getPrice() * (1 - d.getValue() / 100f);
            } else {
                return p.getPrice() - d.getValue();
            }
        }
        return p.getPrice();
    }

}