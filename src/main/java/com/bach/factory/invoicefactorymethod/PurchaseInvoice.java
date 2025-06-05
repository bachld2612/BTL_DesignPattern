package com.bach.factory.invoicefactorymethod;

import com.bach.dao.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseInvoice implements Invoice {
    private int invoiceId;
    private int adminId;
    private double amount;
    private String purchaseDate;
    private String status;

    public PurchaseInvoice() {}

    public PurchaseInvoice(int invoiceId, int adminId, double amount, String purchaseDate, String status) {
        this.invoiceId = invoiceId;
        this.adminId = adminId;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.status = status;
    }

    @Override
    public void saveToDatabase() throws SQLException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO buy_bills (id_buy_bills, id_admin, amount, buy_date, state) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setInt(1, invoiceId);
            stmt.setInt(2, adminId);
            stmt.setDouble(3, amount);
            stmt.setString(4, purchaseDate);
            stmt.setString(5, status);
            stmt.executeUpdate();
            System.out.println("Purchase invoice saved to database.");
        }
    }

    @Override
    public void loadFromDatabase(int id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM buy_bills WHERE id_buy_bills = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.invoiceId = rs.getInt("id_buy_bills");
                this.adminId = rs.getInt("id_admin");
                this.amount = rs.getDouble("amount");
                this.purchaseDate = rs.getString("buy_date");
                this.status = rs.getString("state");
            } else {
                throw new SQLException("Purchase invoice not found with ID: " + id);
            }
        }
    }

    @Override
    public void displayInvoice() {
        System.out.printf("Purchase Invoice - ID: %d, Admin ID: %d, Amount: %.2f, Purchase Date: %s, Status: %s\n",
                invoiceId, adminId, amount, purchaseDate, status);
    }
}
