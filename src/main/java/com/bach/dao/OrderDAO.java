package com.bach.dao;

import java.sql.*;

public class OrderDAO {
    public int createBooking(Connection conn, int customerId, int cartId, double totalAmount) throws SQLException {
        String sql = "INSERT INTO bookings (id_customers, id_carts, start_date, end_date, status, amount) " +
                "VALUES (?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 7 DAY), 'PENDING', ?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, customerId);
            stmt.setInt(2, cartId);
            stmt.setDouble(3, totalAmount);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            ConnectionManager.closeQuietly(rs);
            ConnectionManager.closeQuietly(stmt);
        }
        return -1;
    }

    public void createOrderRecord(Connection conn, int bookingId, double totalAmount, String paymentMethod, String note) throws SQLException {
        String sql = "INSERT INTO orders (id_bookings, order_date, total_amount, status, payment_method, note) " +
                "VALUES (?, CURDATE(), ?, 'PENDING', ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookingId);
            stmt.setDouble(2, totalAmount);
            stmt.setString(3, paymentMethod);
            stmt.setString(4, note);
            stmt.executeUpdate();
        } finally {
            ConnectionManager.closeQuietly(stmt);
        }
    }

    public void applyVoucherToOrder(int orderId, int voucherId) {
        String sql = "UPDATE vouchers SET id_order = ? WHERE id_vouchers = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);
            stmt.setInt(2, voucherId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeQuietly(stmt);
            ConnectionManager.closeQuietly(conn);
        }
    }
} 