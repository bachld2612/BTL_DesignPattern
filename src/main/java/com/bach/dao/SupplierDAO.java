package com.bach.dao;

import com.bach.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

public class SupplierDAO {

    public void createSupplier(Supplier supplier) {

        String query = "INSERT INTO suppliers (name, phone, address, email, id_admin) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try{
            statement = ConnectionManager.getConnection().prepareStatement(query);
            statement.setNString(1, supplier.getName());
            statement.setNString(2, supplier.getPhone());
            statement.setNString(3, supplier.getAddress());
            statement.setNString(4, supplier.getEmail());
            statement.setInt(5, supplier.getAdminId());
            conn = statement.getConnection();
            statement.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            ConnectionManager.closeQuietly(statement);
            ConnectionManager.closeQuietly(conn);
        }

    }

    public void updateSupplier(Supplier supplier) {

        String query = "UPDATE suppliers SET name = ?, phone = ?, address = ?, email = ? WHERE id_suppliers = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = ConnectionManager.getConnection();
            statement = conn.prepareStatement(query);
            statement.setNString(1, supplier.getName());
            statement.setNString(2, supplier.getPhone());
            statement.setNString(3, supplier.getAddress());
            statement.setNString(4, supplier.getEmail());
            statement.setInt(5, supplier.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.closeQuietly(statement);
            ConnectionManager.closeQuietly(conn);
        }

    }

    public void deleteSupplier(int id) {

        String query = "DELETE FROM suppliers WHERE id_suppliers = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = ConnectionManager.getConnection();
            statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.closeQuietly(statement);
            ConnectionManager.closeQuietly(conn);
        }

    }

    public List<Supplier> getAllSuppliers() {

        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM suppliers";
        Connection conn = null;
        PreparedStatement statement = null;
        try{
            conn = ConnectionManager.getConnection();
            statement = conn.prepareStatement(query);
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(resultSet.getInt("id_suppliers"));
                supplier.setName(resultSet.getNString("name"));
                supplier.setPhone(resultSet.getNString("phone"));
                supplier.setAddress(resultSet.getNString("address"));
                supplier.setEmail(resultSet.getNString("email"));
                supplier.setAdminId(resultSet.getInt("id_admin"));
                suppliers.add(supplier);
            }
            return suppliers;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            ConnectionManager.closeQuietly(statement);
            ConnectionManager.closeQuietly(conn);
        }
        return null;
    }

}
