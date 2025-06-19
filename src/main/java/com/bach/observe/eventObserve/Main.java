package com.bach.observe.eventObserve;

import com.bach.dao.ConnectionManager;
import com.bach.model.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws Exception {
        EventManager eventManager = new EventManager();

        // Tạo một Observer (ví dụ: in thông báo ra console)
        Observer consoleObserver = notification -> {
//            System.out.println("Thông báo cho khách hàng ID " + notification.getCustomerId() + ": " + notification.getMessage());
            System.out.println(notification.getMessage());

        };

        // Đăng ký Observer
        eventManager.registerObserver(consoleObserver);

        // Thêm khách hàng vào danh sách đăng ký (giả sử đã có khách hàng ID = 1)
        Connection conn = null;
        PreparedStatement stmt = null;
        String subscribeSql = "INSERT INTO customer_event_subscriptions (id_customer) VALUES (?)";
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.prepareStatement(subscribeSql);
            stmt.setInt(1, 6); // id_customer = 1
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeQuietly(stmt);
            ConnectionManager.closeQuietly(conn);
        }

        // Thêm sự kiện mới
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Event event = new Event(
                "Phim se tap moi",
                sdf.parse("2025-12-24"),
                sdf.parse("2025-12-26"),
                "Gia chi 10$",
                1 // id_admin
        );

        eventManager.addEvent(event);
    }
}
