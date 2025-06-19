package com.bach.view;

import com.bach.dao.discount.DiscountDAO;
import com.bach.model.Discount;
import com.bach.model.Product;
import com.bach.patterns.decorator.ConcreteProduct;
import com.bach.patterns.decorator.DiscountDecorator;
import com.bach.patterns.decorator.ProductComponent;
import com.bach.service.ProductService;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminDiscountView extends JFrame {
    private JTable table;
    private JTextField discountField;
    private JComboBox<String> discountTypeBox;
    private JSpinner startDateSpinner, endDateSpinner;
    private ProductService productService;
    private DiscountDAO discountDAO;

    public AdminDiscountView() {
        setTitle("Thêm giảm giá sản phẩm");
        setSize(1150, 550);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        productService = new ProductService();
        discountDAO = new DiscountDAO();

        String[] cols = {"ID", "Tên", "Giá gốc", "Mô tả", "Loại giảm", "Giá trị", "Bắt đầu", "Kết thúc", "Giá sau giảm"};
        table = new JTable(new javax.swing.table.DefaultTableModel(new String[0][0], cols));
        refreshTable();
        JScrollPane scroll = new JScrollPane(table);

        discountField = new JTextField(5);
        discountTypeBox = new JComboBox<>(new String[]{"Phần trăm", "Số tiền"});

        // Sử dụng JSpinner để chọn ngày
        SpinnerDateModel startModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        startDateSpinner = new JSpinner(startModel);
        startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd"));

        SpinnerDateModel endModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        endDateSpinner = new JSpinner(endModel);
        endDateSpinner.setEditor(new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd"));

        JButton applyBtn = new JButton("Áp dụng giảm giá");
        applyBtn.addActionListener(e -> applyDiscount());

        JButton deleteBtn = new JButton("Xóa giảm giá");
        deleteBtn.addActionListener(e -> deleteDiscount());

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.add(new JLabel("Loại:"));
        bottom.add(discountTypeBox);
        bottom.add(new JLabel("Giá trị:"));
        bottom.add(discountField);
        bottom.add(new JLabel("Bắt đầu:"));
        bottom.add(startDateSpinner);
        bottom.add(new JLabel("Kết thúc:"));
        bottom.add(endDateSpinner);
        bottom.add(applyBtn);
        bottom.add(deleteBtn);

        add(scroll, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private void applyDiscount() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
            return;
        }

        String valueText = discountField.getText().trim();
        if (valueText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị giảm giá!");
            return;
        }

        float value;
        try {
            value = Float.parseFloat(valueText);
            if (value <= 0) {
                JOptionPane.showMessageDialog(this, "Giá trị giảm giá không được <= 0!");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Giá trị giảm giá phải là số!");
            return;
        }

        // Lấy giá trị loại giảm và chuyển sang đúng định dạng lưu DB
        String selectedType = (String) discountTypeBox.getSelectedItem();
        String discountType;
        if ("Phần trăm".equals(selectedType)) {
            discountType = "percent";
            if (value > 100) {
                JOptionPane.showMessageDialog(this, "Phần trăm giảm giá không được vượt quá 100%!");
                return;
            }
        } else if ("Số tiền".equals(selectedType)) {
            discountType = "amount";
        } else {
            JOptionPane.showMessageDialog(this, "Loại giảm giá không hợp lệ!");
            return;
        }

        Date start = (Date) startDateSpinner.getValue();
        Date end = (Date) endDateSpinner.getValue();

        if (end.before(start)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu!");
            return;
        }

        try {
            int productId = Integer.parseInt(table.getValueAt(row, 0).toString());

            Discount existingDiscount = discountDAO.getActiveDiscount(productId);

            Discount d = new Discount();
            d.setProductId(productId);
            d.setDiscountType(discountType); // đã map lại từ tiếng Việt
            d.setValue(value);
            d.setStartDate(start);
            d.setEndDate(end);

            if (existingDiscount != null) {
                d.setId(existingDiscount.getId());
                discountDAO.updateDiscount(d);
                JOptionPane.showMessageDialog(this, "Đã cập nhật giảm giá!");
            } else {
                discountDAO.addDiscount(d);
                JOptionPane.showMessageDialog(this, "Đã thêm giảm giá mới!");
            }

            refreshTable();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi áp dụng giảm giá: " + ex.getMessage());
        }
    }


    private void deleteDiscount() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
            return;
        }

        int productId = Integer.parseInt(table.getValueAt(row, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa giảm giá không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            discountDAO.deleteDiscountByProductId(productId);
            JOptionPane.showMessageDialog(this, "Đã xóa giảm giá!");
            refreshTable();
        }
    }

    private void refreshTable() {
        List<Product> products = productService.getAll();
        String[][] data = new String[products.size()][9];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            Discount discount = discountDAO.getActiveDiscount(product.getId());

            float originalPrice = product.getPrice();

            data[i][0] = String.valueOf(product.getId());
            data[i][1] = product.getName();
            data[i][2] = String.format("%,.0f", originalPrice);
            data[i][3] = product.getDescription();

            if (discount != null) {
                String typeDisplay = switch (discount.getDiscountType()) {
                    case "percent" -> "Phần trăm";
                    case "amount" -> "Số tiền";
                    default -> discount.getDiscountType(); // fallback
                };
                data[i][4] = typeDisplay;

                data[i][5] = String.valueOf(discount.getValue());
                data[i][6] = sdf.format(discount.getStartDate());
                data[i][7] = sdf.format(discount.getEndDate());

                ProductComponent decorated = new DiscountDecorator(new ConcreteProduct(product.getName(), originalPrice), discount.getValue(), discount.getDiscountType());
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
