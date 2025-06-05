package com.bach.factory.invoicefactorymethod;

import com.bach.dao.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesInvoice implements Invoice {
    private int invoiceId;
    private int orderId;
    private int quantity;
    private String bookingDate;
    private String status;

    public SalesInvoice() {}

    public SalesInvoice(int invoiceId, int orderId, int quantity, String bookingDate, String status) {
        this.invoiceId = invoiceId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.bookingDate = bookingDate;
        this.status = status;
    }
    @Override
    public void saveToDatabase() throws SQLException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO sales_bills (id_sales_bills, id_orders, amount, booking_date, state) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setInt(1, invoiceId);
            stmt.setInt(2, orderId);
            stmt.setInt(3, quantity);
            stmt.setString(4, bookingDate);
            stmt.setString(5, status);
            stmt.executeUpdate();
            System.out.println("Sales invoice saved to database.");
        }
    }

    @Override
    public void loadFromDatabase(int id) throws SQLException {
        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sales_bills WHERE id_sales_bills = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.invoiceId = rs.getInt("id_sales_bills");
                this.orderId = rs.getInt("id_orders");
                this.quantity = rs.getInt("amount");
                this.bookingDate = rs.getString("booking_date");
                this.status = rs.getString("state");
            } else {
                throw new SQLException("Sales invoice not found with ID: " + id);
            }
        }
    }

    @Override
    public void displayInvoice() {
        System.out.printf("Sales Invoice - ID: %d, Order ID: %d, Quantity: %d, Booking Date: %s, Status: %s\n",
                invoiceId, orderId, quantity, bookingDate, status);
    }
}
