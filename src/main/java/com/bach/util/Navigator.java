package com.bach.util;

import com.bach.component.Navbar;
import com.bach.controller.InvoicePurchaseController;
import com.bach.controller.LoginController;
import com.bach.controller.RegisterController;
import com.bach.dao.admin.AdminDAO;
import com.bach.dao.order.OrderDAO;
import com.bach.view.invoicesales.InvoiceSalesView;
import com.bach.controller.InvoiceSalesController;

import javax.swing.*;
import java.util.List;
import java.util.Map;

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
            case INVOICESALE:
                currentFrame.dispose();
                OrderDAO orderDAO = new OrderDAO();
                List<Integer> orderIds = orderDAO.getAllOrderIds();
                new InvoiceSalesController(orderIds);
                break;
            case INVOICEPURCHASE:
                currentFrame.dispose();
                AdminDAO adminDAO = new AdminDAO();
                Map<String, Integer> adminMap = adminDAO.getAllAdminMap();
                new InvoicePurchaseController(adminMap);
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
