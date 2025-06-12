package com.bach.view.invoicepurchase;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InvoicePurchasePanel extends JPanel{
    private final JComboBox<String> comboAdminName; // id_admin
    private final JTextField txtAmount;            // amount
    private final JTextField txtBuyDate;           // buy_date
    private final JComboBox<String> comboStatus;   // state
    private final JButton btnCreate;
    private final JButton btnExport;

    public InvoicePurchasePanel(List<String> adminFullnames) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblAdminId = new JLabel("Admin ID:");
        comboAdminName = new JComboBox<>(adminFullnames.toArray(new String[0]));

        JLabel lblAmount = new JLabel("Amount:");
        txtAmount = new JTextField();

        JLabel lblBuyDate = new JLabel("Buy Date (yyyy-MM-dd):");
        txtBuyDate = new JTextField();

        JLabel lblStatus = new JLabel("Status:");
        comboStatus = new JComboBox<>(new String[]{"Not Paid", "Paid"});

        btnCreate = new JButton("Create Invoice");
        btnExport = new JButton("Xuất Hóa Đơn");

        gbc.gridx = 0; gbc.gridy = 0;
        add(lblAdminId, gbc);
        gbc.gridx = 1;
        add(comboAdminName, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(lblAmount, gbc);
        gbc.gridx = 1;
        add(txtAmount, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(lblBuyDate, gbc);
        gbc.gridx = 1;
        add(txtBuyDate, gbc);

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

    public JComboBox<String> getComboAdminName() {
        return comboAdminName;
    }

    public JTextField getTxtAmount() {
        return txtAmount;
    }

    public JTextField getTxtBuyDate() {
        return txtBuyDate;
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
