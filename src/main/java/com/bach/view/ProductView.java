package com.bach.view;

import com.bach.controller.ProductController;
import com.bach.model.product.BasicProductFactory;
import com.bach.model.product.PremiumProductFactory;
import com.bach.model.product.Product;
import com.bach.model.product.ProductFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductView extends JFrame {
    public JTable tblProducts;
    public JButton btnAdd, btnEdit, btnDelete;
    public JTextField txtName, txtDesc, txtPrice;
    public JComboBox<String> cboSupplier, cboAdmin;
    public JRadioButton rdoBasic, rdoPremium;
    public ButtonGroup stateGroup;
    public DefaultTableModel model;
    public ProductController controller = new ProductController();

    public ProductView() {
        setTitle("Quản Lý Sản Phẩm");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("QUẢN LÝ SẢN PHẨM", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField(20);
        formPanel.add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        txtDesc = new JTextField(20);
        formPanel.add(txtDesc, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        txtPrice = new JTextField(20);
        formPanel.add(txtPrice, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Supplier:"), gbc);
        gbc.gridx = 1;
        cboSupplier = new JComboBox<>(new String[]{
                "1 - Công ty A", "2 - Nhà cung cấp B", "3 - Supplier C"
        });
        formPanel.add(cboSupplier, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("Admin:"), gbc);
        gbc.gridx = 1;
        cboAdmin = new JComboBox<>(new String[]{
                "2 - Admin Nguyễn", "3 - Admin Trần", "4 - Admin Lê"
        });
        formPanel.add(cboAdmin, gbc);

        gbc.gridx = 0; gbc.gridy++;
        formPanel.add(new JLabel("State:"), gbc);
        gbc.gridx = 1;
        JPanel statePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        rdoBasic = new JRadioButton("Basic", true);
        rdoPremium = new JRadioButton("Premium");
        stateGroup = new ButtonGroup();
        stateGroup.add(rdoBasic);
        stateGroup.add(rdoPremium);
        statePanel.add(rdoBasic);
        statePanel.add(rdoPremium);
        formPanel.add(statePanel, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnAdd = new JButton("Thêm");
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Supplier", "Name", "Desc", "Price", "State", "Admin"}
        );
        tblProducts = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tblProducts);

        mainPanel.add(formPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(scrollPane);
        add(mainPanel, BorderLayout.CENTER);

        loadData();
        addListeners();
    }

    private void loadData() {
        model.setRowCount(0);
        List<Product> list = controller.getAll();
        for (Product p : list) {
            model.addRow(new Object[]{
                    p.getId_products(),
                    p.getSupplierId(),
                    p.getName(),
                    p.getDescription(),
                    p.getPrice(),
                    p.getState(),
                    p.getAdminId()
            });
        }
    }

    private void addListeners() {
        btnAdd.addActionListener(e -> {
            Product p = buildProductFromForm();
            if (p != null) {
                controller.add(p);
                loadData();
            }
            clearForm();
        });

        btnEdit.addActionListener(e -> {
            int row = tblProducts.getSelectedRow();
            if (row >= 0) {
                Product p = buildProductFromForm();
                if (p != null) {
                    int id = (int) model.getValueAt(row, 0);
                    p.setId_products(id);
                    controller.update(p);
                    loadData();
                }
            }
            clearForm();
        });

        btnDelete.addActionListener(e -> {
            int row = tblProducts.getSelectedRow();
            if (row >= 0) {
                int id = (int) model.getValueAt(row, 0);
                controller.delete(id);
                loadData();
            }
            clearForm();
        });

        tblProducts.getSelectionModel().addListSelectionListener(e -> {
            int row = tblProducts.getSelectedRow();
            if (row >= 0) {
                cboSupplier.setSelectedItem(tblProducts.getValueAt(row, 1).toString() + " - ...");
                txtName.setText(tblProducts.getValueAt(row, 2).toString());
                txtDesc.setText(tblProducts.getValueAt(row, 3).toString());
                txtPrice.setText(tblProducts.getValueAt(row, 4).toString());
                String state = tblProducts.getValueAt(row, 5).toString();
                if (state.equalsIgnoreCase("Premium")) {
                    rdoPremium.setSelected(true);
                } else {
                    rdoBasic.setSelected(true);
                }
                cboAdmin.setSelectedItem(tblProducts.getValueAt(row, 6).toString() + " - ...");
            }
        });
    }

    private Product buildProductFromForm() {
        try {
            int supplierId = Integer.parseInt(cboSupplier.getSelectedItem().toString().split(" - ")[0]);
            String name = txtName.getText();
            String desc = txtDesc.getText();
            double price = Double.parseDouble(txtPrice.getText());
            String state = rdoPremium.isSelected() ? "Premium" : "Basic";
            int adminId = Integer.parseInt(cboAdmin.getSelectedItem().toString().split(" - ")[0]);

            ProductFactory factory = state.equals("Premium")
                    ? new PremiumProductFactory()
                    : new BasicProductFactory();

            return factory.createProduct(supplierId, name, desc, price, state, adminId);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private void clearForm() {
        txtName.setText("");
        txtDesc.setText("");
        txtPrice.setText("");
        cboSupplier.setSelectedIndex(0);
        cboAdmin.setSelectedIndex(0);
        rdoBasic.setSelected(true);
        tblProducts.clearSelection(); // bỏ chọn dòng trong bảng
    }

}
