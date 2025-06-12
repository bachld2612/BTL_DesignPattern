package com.bach.controller;

import com.bach.factory.invoicefactorymethod.Invoice;
import com.bach.factory.invoicefactorymethod.InvoiceFactory;
import com.bach.factory.invoicefactorymethod.SalesInvoiceFactory;
import com.bach.view.invoicesales.InvoiceSalesPanel;
import com.bach.view.invoicesales.InvoiceSalesSearchDialog;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InvoiceSalesController {
    private final InvoiceSalesPanel panel;
    private final InvoiceFactory salesFactory;

    public InvoiceSalesController(InvoiceSalesPanel panel) {
        this.panel = panel;
        this.salesFactory = new SalesInvoiceFactory();
        initController();
    }

    private void initController() {
        panel.getBtnCreate().addActionListener(e -> saveInvoice());
        panel.getBtnExport().addActionListener(e -> openSearchDialog());
    }

    private void saveInvoice() {
        try {
            int orderId = (Integer) panel.getComboOrderId().getSelectedItem();
            int quantity = Integer.parseInt(panel.getTxtQuantity().getText().trim());
            String dateStr = panel.getTxtBookingDate().getText().trim();

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

            Invoice salesInvoice = salesFactory.createInvoice(orderId, quantity, bookingDate, status);

            // lưu vào database
//            boolean success = service.insertInvoice(invoice);
             boolean success = salesInvoice.saveToDatabase();
            System.out.println(success);
            if (success) {
                JOptionPane.showMessageDialog(panel, "Invoice created successfully!");

                // Reset form về mặc định
                panel.getComboOrderId().setSelectedIndex(0);
                panel.getTxtQuantity().setText("");
                panel.getTxtBookingDate().setText("");
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
        InvoiceSalesSearchDialog dialog = new InvoiceSalesSearchDialog(null);

        dialog.getBtnSearch().addActionListener(event -> {
            String idText = dialog.getTxtSearchId().getText().trim();
            if (!idText.isEmpty()) {
                try {
                    int invoiceId = Integer.parseInt(idText);
                    Invoice retrievedSales = salesFactory.retrieveInvoice(invoiceId);
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
