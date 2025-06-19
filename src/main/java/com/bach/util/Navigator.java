package com.bach.util;

import com.bach.component.Navbar;
import com.bach.controller.*;
import com.bach.dao.admin.AdminDAO;
import com.bach.controller.OrderController;
import com.bach.dao.order.OrderDAO;
import com.bach.patterns.sessionsingleton.Session;
import com.bach.view.invoicesales.InvoiceSalesView;
import com.bach.controller.supplier.CreateSupplierController;
import com.bach.controller.supplier.SupplierController;
import com.bach.view.SupplierView;
import com.bach.controller.VoucherManagementController;
import com.bach.view.VoucherManagementView;
import com.bach.view.PointsManagementView;

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
            case ORDER:
                currentFrame.dispose();
                new OrderController();
                break;
            case VOUCHER:
                currentFrame.dispose();
                new VoucherManagementController();
                break;
            case POINTS:
                currentFrame.dispose();
                new PointsManagementView();
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
                String adminFullname = adminDAO.getAdminNameById(Session.getInstance().getId());
                new InvoicePurchaseController(adminFullname);
                break;
            case EVENT:
                currentFrame.dispose();
                new EventController();
                break;
            case EXIT:
                currentFrame.dispose();
                System.exit(0);
                break;
            case SUPPLIER:
                currentFrame.dispose();
                new SupplierController();
                break;

            case CREATE_SUPPLIER:
                currentFrame.dispose();
                new CreateSupplierController();
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
