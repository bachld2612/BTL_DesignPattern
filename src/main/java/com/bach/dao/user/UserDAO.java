package com.bach.dao.user;

import com.bach.dao.ConnectionManager;
import com.bach.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class UserDAO {

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("Id"));
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Trong thực tế, bạn có thể ném exception hoặc log để xử lý ở tầng Service
        }
        return users;
    }

}
