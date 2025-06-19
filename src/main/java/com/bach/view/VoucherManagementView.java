package com.bach.view;

import com.bach.model.Customer;
import com.bach.model.IVoucher;
import com.bach.patterns.factory.VoucherFactory;
import com.bach.patterns.factory.PercentageVoucherFactory;
import com.bach.patterns.factory.FixedAmountVoucherFactory;
import com.bach.service.OrderService;
import com.bach.service.VoucherService;
import com.bach.service.CustomerService;
import com.bach.component.Navbar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VoucherManagementView extends JFrame {
    private JTable voucherTable;
    private JTextField codeField;
    private JTextField nameField;
    private JTextField startValueField;
    private JTextField endValueField;
    private JComboBox<String> discountTypeCombo;
    private JTextField discountValueField;
    private JTable customerTable;
    private OrderService orderService;
    private VoucherService voucherService;
    private List<IVoucher> vouchers;
    private List<Customer> customers;
    private Navbar navbar;
    private JButton editButton, deleteButton;

    public VoucherManagementView() {
        orderService = OrderService.getInstance();
        voucherService = VoucherService.getInstance();
        vouchers = new ArrayList<>();
        customers = new ArrayList<>();
        setTitle("Quản lý voucher");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false);
        navbar = new Navbar(this);
        add(navbar, BorderLayout.NORTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // add(voucherView);
        setVisible(true);
        initializeData();
        initializeUI();
    }

    private void initializeData() {
        // Load vouchers from database
        vouchers = voucherService.getAllVouchers();
        // Load customers from database
        customers = CustomerService.getInstance().getAllCustomers();
    }

    private void initializeUI() {
    
        
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Left panel for voucher management
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Voucher Management"));

        // Voucher table
        String[] columnNames = {"Code", "Name", "Start Value", "End Value", "Discount"};
        Object[][] data = vouchers.stream()
            .map(v -> new Object[]{
                v.getCode(),
                v.getName(),
                v.getStartValue(),
                v.getEndValue(),
                v.getDiscountDescription()
            })
            .toArray(Object[][]::new);
        voucherTable = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(voucherTable);
        leftPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Voucher form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Code
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Code:"), gbc);
        gbc.gridx = 1;
        codeField = new JTextField(15);
        formPanel.add(codeField, gbc);

        // Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        formPanel.add(nameField, gbc);

        // Start Value
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Start Value:"), gbc);
        gbc.gridx = 1;
        startValueField = new JTextField(15);
        formPanel.add(startValueField, gbc);

        // End Value
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("End Value:"), gbc);
        gbc.gridx = 1;
        endValueField = new JTextField(15);
        formPanel.add(endValueField, gbc);

        // Discount Type
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Discount Type:"), gbc);
        gbc.gridx = 1;
        String[] types = {"Percentage", "Fixed Amount"};
        discountTypeCombo = new JComboBox<>(types);
        formPanel.add(discountTypeCombo, gbc);

        // Discount Value
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Discount Value:"), gbc);
        gbc.gridx = 1;
        discountValueField = new JTextField(15);
        formPanel.add(discountValueField, gbc);

        // Create button
        gbc.gridx = 1; gbc.gridy = 6;
        JButton createButton = new JButton("Create Voucher");
        createButton.addActionListener(e -> createVoucher());
        formPanel.add(createButton, gbc);

        // Edit button
        gbc.gridx = 0; gbc.gridy = 7;
        editButton = new JButton("Edit Voucher");
        editButton.addActionListener(e -> editVoucher());
        formPanel.add(editButton, gbc);

        // Delete button
        gbc.gridx = 1; gbc.gridy = 7;
        deleteButton = new JButton("Delete Voucher");
        deleteButton.addActionListener(e -> deleteVoucher());
        formPanel.add(deleteButton, gbc);

        leftPanel.add(formPanel, BorderLayout.SOUTH);

        // Right panel for customer levels
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Customer Levels"));

        // Customer table
        String[] customerColumns = {"Username", "Full Name", "Points", "Level", "Discount"};
        Object[][] customerData = customers.stream()
            .map(c -> new Object[]{
                c.getUsername(),
                c.getFullName(),
                c.getPoints(),
                c.getLevel().getLevelName(),
                c.getLevel().getDiscountRate() * 100 + "%"
            })
            .toArray(Object[][]::new);
        customerTable = new JTable(customerData, customerColumns);
        JScrollPane customerScrollPane = new JScrollPane(customerTable);
        rightPanel.add(customerScrollPane, BorderLayout.CENTER);

        // Add panels to main panel
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        // Khi chọn một dòng trên bảng voucher, điền thông tin lên form
        voucherTable.getSelectionModel().addListSelectionListener(e -> fillVoucherFormFromTable());
    }

    private void createVoucher() {
        try {
            String code = codeField.getText().trim();
            String name = nameField.getText().trim();
            double startValue = Double.parseDouble(startValueField.getText().trim());
            double endValue = Double.parseDouble(endValueField.getText().trim());
            String discountType = (String) discountTypeCombo.getSelectedItem();
            double discountValue = Double.parseDouble(discountValueField.getText().trim());

            IVoucher voucher;
            if ("Percentage".equals(discountType)) {
                voucher = new PercentageVoucherFactory().createVoucher(
                    vouchers.size() + 1, code, name, startValue, endValue, discountValue);
            } else {
                voucher = new FixedAmountVoucherFactory().createVoucher(
                    vouchers.size() + 1, code, name, startValue, endValue, discountValue);
            }

            // Save voucher to database
            voucherService.saveVoucher(voucher);
            
            // Refresh the voucher list
            vouchers = voucherService.getAllVouchers();
            refreshVoucherTable();
            clearVoucherForm();

            JOptionPane.showMessageDialog(this, "Voucher created successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for values!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error creating voucher: " + e.getMessage());
        }
    }

    private void refreshVoucherTable() {
        String[] columnNames = {"Code", "Name", "Start Value", "End Value", "Discount"};
        Object[][] data = vouchers.stream()
            .map(v -> new Object[]{
                v.getCode(),
                v.getName(),
                v.getStartValue(),
                v.getEndValue(),
                v.getDiscountDescription()
            })
            .toArray(Object[][]::new);
        voucherTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private void clearVoucherForm() {
        codeField.setText("");
        nameField.setText("");
        startValueField.setText("");
        endValueField.setText("");
        discountValueField.setText("");
    }

    private void fillVoucherFormFromTable() {
        int row = voucherTable.getSelectedRow();
        if (row >= 0 && row < vouchers.size()) {
            IVoucher v = vouchers.get(row);
            codeField.setText(v.getCode());
            nameField.setText(v.getName());
            startValueField.setText(String.valueOf(v.getStartValue()));
            endValueField.setText(String.valueOf(v.getEndValue()));
            discountValueField.setText(String.valueOf(v.getDiscountDescription().contains("%") ? ((com.bach.model.PercentageVoucher)v).getPercentage() : ((com.bach.model.FixedAmountVoucher)v).getAmount()));
            if (v.getDiscountDescription().contains("%")) {
                discountTypeCombo.setSelectedItem("Percentage");
            } else {
                discountTypeCombo.setSelectedItem("Fixed Amount");
            }
        }
    }

    private void editVoucher() {
        int row = voucherTable.getSelectedRow();
        if (row < 0 || row >= vouchers.size()) {
            JOptionPane.showMessageDialog(this, "Please select a voucher to edit.");
            return;
        }
        try {
            IVoucher oldVoucher = vouchers.get(row);
            String code = codeField.getText().trim();
            String name = nameField.getText().trim();
            double startValue = Double.parseDouble(startValueField.getText().trim());
            double endValue = Double.parseDouble(endValueField.getText().trim());
            String discountType = (String) discountTypeCombo.getSelectedItem();
            double discountValue = Double.parseDouble(discountValueField.getText().trim());
            IVoucher newVoucher;
            if ("Percentage".equals(discountType)) {
                newVoucher = new PercentageVoucherFactory().createVoucher(
                    oldVoucher.getId(), code, name, startValue, endValue, discountValue);
            } else {
                newVoucher = new FixedAmountVoucherFactory().createVoucher(
                    oldVoucher.getId(), code, name, startValue, endValue, discountValue);
            }
            voucherService.updateVoucher(newVoucher);
            vouchers = voucherService.getAllVouchers();
            refreshVoucherTable();
            JOptionPane.showMessageDialog(this, "Voucher updated successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error updating voucher: " + e.getMessage());
        }
    }

    private void deleteVoucher() {
        int row = voucherTable.getSelectedRow();
        if (row < 0 || row >= vouchers.size()) {
            JOptionPane.showMessageDialog(this, "Please select a voucher to delete.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this voucher?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            IVoucher voucher = vouchers.get(row);
            voucherService.deactivateVoucher(voucher.getId());
            vouchers = voucherService.getAllVouchers();
            refreshVoucherTable();
            clearVoucherForm();
            JOptionPane.showMessageDialog(this, "Voucher deleted successfully!");
        }
    }
} 