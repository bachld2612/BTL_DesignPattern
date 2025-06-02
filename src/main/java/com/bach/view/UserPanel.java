package com.bach.view;


import com.bach.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// Panel chứa JTable và nút Load, tương tác với Controller qua listener
public class UserPanel extends JPanel {
    private final JTable table;
    private final DefaultTableModel tableModel;
    private final JButton btnLoad;

    public UserPanel() {
        setLayout(new BorderLayout());

        // Khởi tạo DefaultTableModel với tên cột tương ứng User
        tableModel = new DefaultTableModel(new Object[]{"ID", "Username", "Full Name", "Email", "Created At"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho chỉnh sửa trực tiếp
            }
        };
        table = new JTable(tableModel);

        // Tạo nút Load
        btnLoad = new JButton("Load Users");

        // Đặt bố cục: nút ở phía trên, table cuộn ở giữa
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnLoad);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    // Phương thức do Controller gọi, truyền danh sách User vào để hiển thị
    public void populateTable(List<User> users) {
        // Xóa hết dữ liệu cũ
        tableModel.setRowCount(0);

        // Thêm từng User vào tableModel
        for (User u : users) {
            Object[] row = new Object[]{
                    u.getId(),
            };
            tableModel.addRow(row);
        }
    }

    public JButton getBtnLoad() {
        return btnLoad;
    }
}