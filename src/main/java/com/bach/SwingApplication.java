package com.bach;


import com.bach.controller.UserController;
import com.bach.view.UserPanel;

import javax.swing.*;

public class SwingApplication {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // (2) Chạy trên Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("User Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 450);
            frame.setLocationRelativeTo(null);

            // Tạo panel và controller
            UserPanel userPanel = new UserPanel();
            new UserController(userPanel);

            frame.setContentPane(userPanel);
            frame.setVisible(true);
        });
    }
}