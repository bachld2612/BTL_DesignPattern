package com.bach.view;

import com.bach.dao.discount.DiscountDAO;
import com.bach.model.Discount;
import com.bach.model.Product;
import com.bach.service.ProductService;
import com.bach.patterns.decorator.ConcreteProduct;
import com.bach.patterns.decorator.DiscountDecorator;
import com.bach.patterns.decorator.ProductComponent;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminDiscountView extends JFrame {
    private JTable table;
    private JTextField discountField, startDateField, endDateField;
    private JComboBox<String> discountTypeBox;
    private ProductService productService;
    private DiscountDAO discountDAO;

    public AdminDiscountView() {
        setTitle("Thêm giảm giá sản phẩm");
        setSize(1100, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        productService = new ProductService();
        discountDAO = new DiscountDAO();

        // Giao diện bảng hiển thị sản phẩm và giảm giá
        String[] cols = {"ID", "Tên", "Giá gốc", "Mô tả", "Loại giảm", "Giá trị", "Bắt đầu", "Kết thúc", "Giá sau giảm"};
        table = new JTable(new javax.swing.table.DefaultTableModel(new String[0][0], cols));
        refreshTable();
        JScrollPane scroll = new JScrollPane(table);

        // Giao diện nhập liệu giảm giá
        discountField = new JTextField(5);
        startDateField = new JTextField(10); // yyyy-MM-dd
        endDateField = new JTextField(10);
        discountTypeBox = new JComboBox<>(new String[]{"percent", "amount"});

        JButton applyBtn = new JButton("Áp dụng giảm giá");
        applyBtn.addActionListener(e -> applyDiscount());

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.add(new JLabel("Loại:"));
        bottom.add(discountTypeBox);
        bottom.add(new JLabel("Giá trị:"));
        bottom.add(discountField);
        bottom.add(new JLabel("Bắt đầu (yyyy-MM-dd):"));
        bottom.add(startDateField);
        bottom.add(new JLabel("Kết thúc:"));
        bottom.add(endDateField);
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

        try {
            int productId = Integer.parseInt(table.getValueAt(row, 0).toString());
            float value = Float.parseFloat(discountField.getText());

            if (value < 0) {
                JOptionPane.showMessageDialog(this, "Giá trị giảm giá không hợp lệ!");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date start = sdf.parse(startDateField.getText());
            Date end = sdf.parse(endDateField.getText());

            // Kiểm tra xem có giảm giá chưa
            Discount existingDiscount = discountDAO.getActiveDiscount(productId);

            Discount d = new Discount();
            d.setProductId(productId);
            d.setDiscountType((String) discountTypeBox.getSelectedItem());
            d.setValue(value);
            d.setStartDate(start);
            d.setEndDate(end);

            if (existingDiscount != null) {
                d.setId(existingDiscount.getId()); // Gán ID để update
                discountDAO.updateDiscount(d);
                JOptionPane.showMessageDialog(this, "Đã cập nhật giảm giá!");
            } else {
                discountDAO.addDiscount(d);
                JOptionPane.showMessageDialog(this, "Đã thêm giảm giá mới!");
            }

            refreshTable();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ: " + ex.getMessage());
        }
    }


    private void refreshTable() {
        List<Product> products = productService.getAll();
        String[][] data = new String[products.size()][9];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Discount discount = discountDAO.getActiveDiscount(product.getId());

            data[i][0] = String.valueOf(product.getId());
            data[i][1] = product.getName();
            data[i][2] = String.format("%,.0f", product.getPrice());
            data[i][3] = product.getDescription();

            if (discount != null) {
                data[i][4] = discount.getDiscountType();
                data[i][5] = String.valueOf(discount.getValue());
                data[i][6] = sdf.format(discount.getStartDate());
                data[i][7] = sdf.format(discount.getEndDate());

                // ✅ Tính giá sau giảm bằng Decorator
                ProductComponent original = new ConcreteProduct(product.getName(), product.getPrice());
                ProductComponent decorated = new DiscountDecorator(original, discount.getValue(), discount.getDiscountType());
                float finalPrice = Math.max(0, decorated.getPrice());
                data[i][8] = String.format("%,.0f", finalPrice);
            } else {
                data[i][4] = "—";
                data[i][5] = "—";
                data[i][6] = "—";
                data[i][7] = "—";
                data[i][8] = "—";
            }
        }

        String[] cols = {"ID", "Tên", "Giá gốc", "Mô tả", "Loại giảm", "Giá trị", "Bắt đầu", "Kết thúc", "Giá sau giảm"};
        table.setModel(new javax.swing.table.DefaultTableModel(data, cols));
    }

}
