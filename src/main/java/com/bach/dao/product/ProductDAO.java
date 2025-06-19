package com.bach.dao.product;

<<<<<<< HEAD
import com.bach.dao.ConnectionManager;
import com.bach.model.Product;

=======
>>>>>>> 6df2e14c0a8b253c5f2f5220e3fa456d59dc4888
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class ProductDAO {

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id_products"));
                p.setName(rs.getString("name_products"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getFloat("price"));
                p.setState(rs.getString("state"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updatePrice(int productId, float newPrice) {
        String sql = "UPDATE products SET price = ? WHERE id_products = ?";
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setFloat(1, newPrice);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
=======
import com.bach.dao.ConnectionManager;
import com.bach.model.product.BasicProductFactory;
import com.bach.model.product.PremiumProductFactory;
import com.bach.model.product.Product;
import com.bach.model.product.ProductFactory;

public class ProductDAO {
    public List<Product> findAll() {
        List<Product> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_products");
                int supId = rs.getInt("id_suppliers");
                String name = rs.getString("name_products");
                String desc = rs.getString("description");
                double price = rs.getDouble("price");
                String state = rs.getString("state");
                int adminId = rs.getInt("id_admin");

                // Dùng Abstract để tạo sản phẩm phù hợp
                ProductFactory factory;
                if ("Premium".equalsIgnoreCase(state)) {
                    factory = new PremiumProductFactory();
                } else {
                    factory = new BasicProductFactory();
                }

                Product product = factory.createProduct(id , supId, name, desc, price, state, adminId);
                danhSach.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return danhSach;
    }


    public void insert(Product product) {
        String sql = "INSERT INTO products (id_suppliers, name_products, description, price, state, id_admin) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // stmt.setInt(1, product.getId());
            stmt.setInt(1, product.getSupplierId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setString(5, product.getState());
            stmt.setInt(6, product.getAdminId());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(Product product) {
        String sql = "UPDATE products SET id_suppliers = ?, name_products = ?, description = ?, price = ?, state = ?, id_admin = ? WHERE id_products = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            //stmt.setInt(7, product.getId());
            stmt.setInt(1, product.getSupplierId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setString(5, product.getState());
            stmt.setInt(6, product.getAdminId());
            stmt.setInt(7, product.getId_products());
            System.out.println(product.getId_products());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id_products = ?";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
>>>>>>> 6df2e14c0a8b253c5f2f5220e3fa456d59dc4888
