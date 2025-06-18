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
    }

    public void deleteSupplier(int id) {
    }

    public List<Supplier> getAllSuppliers() {
        return new ArrayList<>();
    }

}
