package com.bach.util;

import com.bach.component.Navbar;
import com.bach.controller.LoginController;
import com.bach.controller.OrderController;
import com.bach.controller.RegisterController;
import com.bach.controller.VoucherManagementController;
import com.bach.view.VoucherManagementView;

import javax.swing.*;

public class Navigator {

    public static void navigate(Navbar.NavItem navItem, JFrame currentFrame) {
        switch (navItem) {
            case LOGIN:
                currentFrame.dispose();
                new LoginController();
                break;
            case REGISTER:
                currentFrame.dispose();
                new RegisterController();
                break;
            case ORDER:
                currentFrame.dispose();
                new OrderController();
                break;
            case VOUCHER:
                currentFrame.dispose();
                new VoucherManagementController();
                break;
            case EXIT:
                currentFrame.dispose();
                System.exit(0);
                break;
            default:
                throw new IllegalArgumentException("Unknown navigation item: " + navItem);
        }
    }

}
