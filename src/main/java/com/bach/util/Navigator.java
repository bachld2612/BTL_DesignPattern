package com.bach.util;

import com.bach.component.Navbar;
import com.bach.controller.LoginController;
import com.bach.controller.ProductController;
import com.bach.controller.RegisterController;
import com.bach.view.ProductView;

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
            case EXIT:
                currentFrame.dispose();
                System.exit(0);
                break;
            case PRODUCT:
                currentFrame.dispose();
                new ProductView().setVisible(true);
                break;
            default:
                throw new IllegalArgumentException("Unknown navigation item: " + navItem);
        }
    }

}
