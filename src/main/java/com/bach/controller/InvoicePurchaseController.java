package com.bach.controller;

import com.bach.factory.invoicefactorymethod.Invoice;
import com.bach.factory.invoicefactorymethod.InvoiceFactory;
import com.bach.factory.invoicefactorymethod.PurchaseInvoiceFactory;
import com.bach.factory.invoicefactorymethod.SalesInvoiceFactory;
import com.bach.view.invoicepurchase.InvoicePurchasePanel;
import com.bach.view.invoicepurchase.InvoicePurchaseSearchDialog;
import com.bach.view.invoicesales.InvoiceSalesPanel;
import com.bach.view.invoicesales.InvoiceSalesSearchDialog;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class InvoicePurchaseController {
    private final InvoicePurchasePanel panel;
    private final Map<String, Integer> adminMap;
    private final InvoiceFactory purchaseFactory;

    public InvoicePurchaseController(InvoicePurchasePanel panel , Map<String, Integer> adminMap) {
        this.panel = panel;
        this.purchaseFactory = new PurchaseInvoiceFactory();
        this.adminMap = adminMap;
        initController();
    }

    private void initController() {
        panel.getBtnCreate().addActionListener(e -> saveInvoice());
        panel.getBtnExport().addActionListener(e -> openSearchDialog());
    }

    private void saveInvoice() {
        try {
            String selectedAdminName = (String) panel.getComboAdminName().getSelectedItem();
            int adminId = adminMap.getOrDefault(selectedAdminName, -1);
//            if (adminId == -1) {
//                JOptionPane.showMessageDialog(frame, "Không tìm thấy ID của admin được chọn.", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
            double amount = Double.parseDouble(panel.getTxtAmount().getText().trim());
            String dateStr = panel.getTxtBuyDate().getText().trim();

            // Kiểm tra định dạng ngày hợp lệ (yyyy-MM-dd)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate.parse(dateStr, formatter); // chỉ để validate, không cần giữ lại
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày đúng định dạng yyyy-MM-dd.");
                return;
            }

            // bookingDate giữ nguyên là String
            String bookingDate = dateStr;

            String status = (String) panel.getComboStatus().getSelectedItem();

            // Tạo đối tượng InvoiceSale
//            InvoiceSale invoice = new InvoiceSale();
//            invoice.setOrderId(orderId);
//            invoice.setQuantity(quantity);
//            invoice.setBookingDate(bookingDate);
//            invoice.setStatus(status);

            Invoice salesInvoice = purchaseFactory.createInvoice(adminId, amount, bookingDate, status);

            // lưu vào database
//            boolean success = service.insertInvoice(invoice);
            boolean success = salesInvoice.saveToDatabase();
            System.out.println(success);
            if (success) {
                JOptionPane.showMessageDialog(panel, "Invoice created successfully!");

                // Reset form về mặc định
                panel.getComboAdminName().setSelectedIndex(0);
                panel.getTxtAmount().setText("");
                panel.getTxtBuyDate().setText("");
                panel.getComboStatus().setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(panel, "Failed to create invoice.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(panel, "Quantity must be a number.");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(panel, "Invalid date format. Use yyyy-MM-dd.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void openSearchDialog() {
        InvoicePurchaseSearchDialog dialog = new InvoicePurchaseSearchDialog(null);

        dialog.getBtnSearch().addActionListener(event -> {
            String idText = dialog.getTxtSearchId().getText().trim();
            if (!idText.isEmpty()) {
                try {
                    int invoiceId = Integer.parseInt(idText);
                    Invoice retrievedSales = purchaseFactory.retrieveInvoice(invoiceId);
                    String result = retrievedSales.exportInvoice(invoiceId);
                    dialog.displayResult(result);
                } catch (NumberFormatException ex) {
                    dialog.displayResult("ID không hợp lệ.");
                } catch (SQLException e) {
                    dialog.displayResult("Lỗi khi tìm kiếm hóa đơn.");
                }
            }
        });

        dialog.setVisible(true);
    }
}
