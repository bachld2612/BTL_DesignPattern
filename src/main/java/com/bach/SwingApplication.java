package com.bach;


import com.bach.controller.LoginController;

import javax.swing.*;

public class SwingApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginController();
        });
    }
}