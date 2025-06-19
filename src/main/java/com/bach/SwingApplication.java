package com.bach;


import com.bach.controller.LoginController;
import com.bach.view.CustomerMainView;
import com.bach.view.LoginView;

import javax.swing.*;

public class SwingApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
        });
    }
}