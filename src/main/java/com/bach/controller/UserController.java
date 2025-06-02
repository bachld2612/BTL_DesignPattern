package com.bach.controller;

import com.bach.model.User;
import com.bach.service.UserService;
import com.bach.view.UserPanel;

import java.util.List;

// Controller chịu trách nhiệm gắn sự kiện lên View và gọi Service để lấy dữ liệu
public class UserController {
    private final UserService userService;
    private final UserPanel userPanel;

    public UserController(UserPanel panel) {
        this.userService = new UserService();
        this.userPanel = panel;
        initController();
    }

    private void initController() {
        // Gắn event cho nút "Load Users"
        userPanel.getBtnLoad().addActionListener(e -> loadUsers());
        // Lần đầu khởi tạo, bạn có thể tự động load hoặc để người dùng bấm nút
        // loadUsers();
    }

    // Phương thức gọi service để lấy dữ liệu và trả về cho View
    private void loadUsers() {
        // Lấy danh sách User từ service
        List<User> list = userService.getAllUsers();

        // Đẩy vào tableModel trong UserPanel
        userPanel.populateTable(list);
    }
}