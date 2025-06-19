package com.bach;


import com.bach.controller.LoginController;
import com.bach.view.OrderView;
import com.bach.view.VoucherManagementView;

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