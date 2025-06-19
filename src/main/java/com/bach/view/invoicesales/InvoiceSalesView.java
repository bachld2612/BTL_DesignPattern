package com.bach.view.invoicesales;

import com.bach.component.Navbar;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InvoiceSalesView extends JFrame {
    private final JComboBox<Integer> comboOrderId;
    private final JTextField txtQuantity;
    private final JTextField txtBookingDate;
    private final JComboBox<String> comboStatus;
    private final JButton btnCreate;
    private final JButton btnExport;
    private Navbar navbar;

    public InvoiceSalesView(List<Integer> orderIds) {
        setTitle("Quản lý Hóa Đơn Bán");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        navbar = new Navbar(this);
        navbar.setVisible(true);
        navbar.setLocation(0, 0);
        add(navbar, BorderLayout.NORTH);

        // Panel chứa form
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

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

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(lblOrderId, gbc);
        gbc.gridx = 1;
        panel.add(comboOrderId, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        panel.add(lblQuantity, gbc);
        gbc.gridx = 1;
        panel.add(txtQuantity, gbc);

        gbc.gridx = 0; gbc.gridy = ++y;
        panel.add(lblBookingDate, gbc);
        gbc.gridx = 1;
        panel.add(txtBookingDate, gbc);

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

    // ✅ Getters nếu muốn xử lý bên ngoài
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

    // ✅ Hàm chạy thử
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            List<Integer> demoOrderIds = List.of(1001, 1002, 1003);
//            new InvoiceSalesView(demoOrderIds).setVisible(true);
//        });
//    }
}
