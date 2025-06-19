package com.bach.service;

import com.bach.dao.ConnectionManager;
import com.bach.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartService {
    private static CartService instance;

    private CartService() {}

    public static CartService getInstance() {
        if (instance == null) {
            instance = new CartService();
        }
        return instance;
    }

    public List<Product> getCartProducts(int customerId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.* FROM products p " +
                    "JOIN product_carts pc ON p.id_products = pc.id_product " +
                    "JOIN carts c ON pc.id_carts = c.id_carts " +
                    "WHERE c.id_customers = ?";
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id_products"));
                product.setName(rs.getString("name_products"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setState(rs.getString("state"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeQuietly(rs);
            ConnectionManager.closeQuietly(stmt);
            ConnectionManager.closeQuietly(conn);
        }
        return products;
    }

    public void addToCart(int customerId, int productId) {
        // First get or create cart for customer
        int cartId = getOrCreateCart(customerId);
        
        // Then add product to cart
        String sql = "INSERT INTO product_carts (id_carts, id_product) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cartId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeQuietly(stmt);
            ConnectionManager.closeQuietly(conn);
        }
    }

    private int getOrCreateCart(int customerId) {
        String sql = "SELECT id_carts FROM carts WHERE id_customers = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("id_carts");
            } else {
                // Create new cart
                String insertSql = "INSERT INTO carts (id_customers) VALUES (?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                insertStmt.setInt(1, customerId);
                insertStmt.executeUpdate();
                
                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeQuietly(rs);
            ConnectionManager.closeQuietly(stmt);
            ConnectionManager.closeQuietly(conn);
        }
        return -1;
    }

    public void clearCart(int customerId) {
        int cartId = getOrCreateCart(customerId);
        String sql = "DELETE FROM product_carts WHERE id_carts = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cartId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeQuietly(stmt);
            ConnectionManager.closeQuietly(conn);
        }
    }
} 