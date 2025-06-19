package com.bach.view.invoicepurchase;

import com.bach.component.Navbar;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InvoicePurchaseView extends JFrame {
    private final JComboBox<String> comboAdminName; // id_admin
    private final JTextField txtAmount;             // amount
    private final JTextField txtBuyDate;            // buy_date
    private final JComboBox<String> comboStatus;    // state
    private final JButton btnCreate;
    private final JButton btnExport;
    private Navbar navbar;

    public InvoicePurchaseView(List<String> adminFullnames) {
        setTitle("Hóa đơn nhập hàng");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        navbar = new Navbar(this);
        navbar.setVisible(true);
        navbar.setLocation(0, 0);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblAdminId = new JLabel("Admin:");
        comboAdminName = new JComboBox<>(adminFullnames.toArray(new String[0]));

        JLabel lblAmount = new JLabel("Amount:");
        txtAmount = new JTextField();

        JLabel lblBuyDate = new JLabel("Buy Date (yyyy-MM-dd):");
        txtBuyDate = new JTextField();

        JLabel lblStatus = new JLabel("Status:");
        comboStatus = new JComboBox<>(new String[]{"Not Paid", "Paid"});

        btnCreate = new JButton("Create Invoice");
        btnExport = new JButton("Xuất Hóa Đơn");

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(lblAdminId, gbc);
        gbc.gridx = 1;
        panel.add(comboAdminName, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        panel.add(lblAmount, gbc);
        gbc.gridx = 1;
        panel.add(txtAmount, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        panel.add(lblBuyDate, gbc);
        gbc.gridx = 1;
        panel.add(txtBuyDate, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        panel.add(lblStatus, gbc);
        gbc.gridx = 1;
        panel.add(comboStatus, gbc);

        gbc.gridx = 1; gbc.gridy = ++y;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(btnCreate, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(btnExport, gbc);

        add(panel);
    }

    // Getter cho Controller dùng
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

    // Hàm main để chạy test form
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            List<String> admins = List.of("Nguyễn Văn A", "Trần Thị B", "Lê Văn C");
//            new InvoicePurchaseView(admins).setVisible(true);
//        });
//    }
}
