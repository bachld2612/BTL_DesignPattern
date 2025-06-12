package com.bach.view.invoicesales;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InvoiceSalesPanel extends JPanel {
//    private final JTextField txtInvoiceId;
    private final JComboBox<Integer> comboOrderId;
    private final JTextField txtQuantity;
    private final JTextField txtBookingDate;
    private final JComboBox<String> comboStatus;
    private final JButton btnCreate;
    private final JButton btnExport;

    // ✅ Đổi tên constructor cho đúng
    public InvoiceSalesPanel(List<Integer> orderIds) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

//        JLabel lblInvoiceId = new JLabel("Invoice ID:");
//        txtInvoiceId = new JTextField();

        JLabel lblOrderId = new JLabel("Order ID:");
        comboOrderId = new JComboBox<>(orderIds.toArray(new Integer[0]));

        JLabel lblQuantity = new JLabel("Quantity:");
        txtQuantity = new JTextField();

        JLabel lblBookingDate = new JLabel("Booking Date (yyyy-MM-dd):");
        txtBookingDate = new JTextField();

        JLabel lblStatus = new JLabel("Status:");
        comboStatus = new JComboBox<>(new String[]{"Not Paid", "Paid"});

        btnCreate = new JButton("Create Invoice");

        btnExport = new JButton("Xuất Hóa Đơn");
//        gbc.gridx = 0; gbc.gridy = 0;
//        add(lblInvoiceId, gbc);
//        gbc.gridx = 1;
//        add(txtInvoiceId, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(lblOrderId, gbc);
        gbc.gridx = 1;
        add(comboOrderId, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(lblQuantity, gbc);
        gbc.gridx = 1;
        add(txtQuantity, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(lblBookingDate, gbc);
        gbc.gridx = 1;
        add(txtBookingDate, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(lblStatus, gbc);
        gbc.gridx = 1;
        add(comboStatus, gbc);

        gbc.gridx = 1; gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        add(btnCreate, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        add(btnExport, gbc);
    }
//
//    public JTextField getTxtInvoiceId() {
//        return txtInvoiceId;
//    }

    public JComboBox<Integer> getComboOrderId() {
        return comboOrderId;
    }

    public JTextField getTxtQuantity() {
        return txtQuantity;
    }

    public JTextField getTxtBookingDate() {
        return txtBookingDate;
    }

    public JComboBox<String> getComboStatus() {
        return comboStatus;
    }

    public JButton getBtnCreate() {
        return btnCreate;
    }

    public JButton getBtnExport() {
        return btnExport;
    }
}


