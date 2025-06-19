package com.bach;


import com.bach.controller.CustomerMainController;
import com.bach.controller.EventController;
import com.bach.controller.LoginController;
import com.bach.controller.NotificationController;
import com.bach.view.CustomerMainView;
import com.bach.view.EventView;
import com.bach.view.LoginView;
import com.bach.view.NotificationView;

import javax.swing.*;

public class SwingApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginController();
        });
    }
}