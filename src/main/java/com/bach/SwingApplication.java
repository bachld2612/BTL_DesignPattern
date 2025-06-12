package com.bach;


import com.bach.controller.InvoicePurchaseController;
import com.bach.controller.InvoiceSalesController;
import com.bach.dao.admin.AdminDAO;
import com.bach.dao.order.OrderDAO;
import com.bach.view.invoicepurchase.InvoicePurchasePanel;
import com.bach.view.invoicesales.InvoiceSalesPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwingApplication {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

//
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Invoice Management");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(600, 400);
//            frame.setLocationRelativeTo(null);
//            OrderDAO orderDAO = new OrderDAO();
//            // Truyền Order ID mẫu
//            List<Integer> orderIds = orderDAO.getAllOrderIds();
//            InvoiceSalesPanel invoicePanel = new InvoiceSalesPanel(orderIds);
//            new InvoiceSalesController(invoicePanel);
//            frame.setContentPane(invoicePanel);
//            frame.setVisible(true);
//        });
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Invoice Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            AdminDAO adminDAO = new AdminDAO();
            Map<String, Integer> adminMap = adminDAO.getAllAdminMap();
            List<String> adminNames = new ArrayList<>(adminMap.keySet());

            InvoicePurchasePanel invoicePurchasePanel = new InvoicePurchasePanel(adminNames);
//            new InvoiceSalesController(invoicePanel);
            new InvoicePurchaseController(invoicePurchasePanel,adminMap);
            frame.setContentPane(invoicePurchasePanel);
            frame.setVisible(true);
        });
    }
}