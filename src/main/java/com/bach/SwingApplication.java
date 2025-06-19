package com.bach;


import com.bach.controller.LoginController;

import javax.swing.*;

public class SwingApplication {
    private static OrderView orderView;
    private static VoucherManagementView voucherView;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginController();
        });
    }
}