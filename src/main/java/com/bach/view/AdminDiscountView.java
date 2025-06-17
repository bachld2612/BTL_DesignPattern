package com.bach.view;

import com.bach.model.Product;
import com.bach.patterns.decorator.ConcreteProduct;
import com.bach.patterns.decorator.DiscountDecorator;
import com.bach.patterns.decorator.FixedAmountDiscountDecorator;
import com.bach.patterns.decorator.ProductComponent;
import com.bach.service.ProductService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminDiscountView extends JFrame {
    private JTable table;
    private JTextField discountField;
    private JComboBox<String> discountTypeBox;
    private ProductService productService;

    public AdminDiscountView() {
        setTitle("Thêm giảm giá sản phẩm");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        productService = new ProductService();
        List<Product> products = productService.getAll();

        String[] cols = {"ID", "Tên", "Giá", "Mô tả"};
        String[][] data = new String[products.size()][4];
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            data[i][0] = String.valueOf(p.getId());
            data[i][1] = p.getName();
            data[i][2] = String.format("%,.0f", p.getPrice());
            data[i][3] = p.getDescription();
        }

        table = new JTable(data, cols);
        JScrollPane scroll = new JScrollPane(table);

        discountField = new JTextField(10);

        // Thêm ComboBox chọn kiểu giảm
        discountTypeBox = new JComboBox<>(new String[]{"% giảm", "Giảm số tiền"});

        JButton applyBtn = new JButton("Áp dụng giảm giá");
        applyBtn.addActionListener(e -> applyDiscount());

        JPanel bottom = new JPanel();
        bottom.add(new JLabel("Loại:"));
        bottom.add(discountTypeBox);
        bottom.add(new JLabel("Nhập giá trị:"));
        bottom.add(discountField);
        bottom.add(applyBtn);

        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private void applyDiscount() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
            return;
        }

        String type = (String) discountTypeBox.getSelectedItem();
        try {
            float input = Float.parseFloat(discountField.getText());
            if (input < 0) throw new NumberFormatException();

            Product p = productService.getAll().get(row);
            ProductComponent original = new ConcreteProduct(p.getName(), p.getPrice());
            ProductComponent discounted;

            if ("% giảm".equals(type)) {
                if (input > 100) throw new NumberFormatException();
                discounted = new DiscountDecorator(original, input);
            } else {
                discounted = new FixedAmountDiscountDecorator(original, input);
            }

            float newPrice = discounted.getPrice();
            productService.updatePrice(p.getId(), newPrice);

            JOptionPane.showMessageDialog(this, "Đã áp dụng giảm giá! Giá mới: " + String.format("%,.0f", newPrice));
            refreshTable();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Giá trị nhập không hợp lệ!");
        }
    }

    private void refreshTable() {
        List<Product> products = productService.getAll();
        String[][] data = new String[products.size()][4];
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            data[i][0] = String.valueOf(p.getId());
            data[i][1] = p.getName();
            data[i][2] = String.format("%,.0f", p.getPrice());
            data[i][3] = p.getDescription();
        }

        String[] cols = {"ID", "Tên", "Giá", "Mô tả"};
        table.setModel(new javax.swing.table.DefaultTableModel(data, cols));
    }
}
