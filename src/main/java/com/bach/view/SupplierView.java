package com.bach.view;

import com.bach.component.Navbar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class SupplierView extends JFrame {
    private JTable supplierTable;
    private Navbar navbar;
    private DefaultTableModel tableModel;

    public SupplierView() {
        setTitle("Nhà cung cấp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(true);

        navbar = new Navbar(this);
        add(navbar, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("Nhà cung cấp", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.PAGE_START);

        String[] columnNames = {"ID", "Tên nhà cung cấp", "Số điện thoại", "Địa chỉ", "Email", "Hành động"};
        tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only the action column is editable (for buttons)
                return column == 5;
            }
        };
        supplierTable = new JTable(tableModel);
        supplierTable.setRowHeight(32);
        supplierTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        supplierTable.setFillsViewportHeight(true);

        // Custom renderer for action buttons
        supplierTable.getColumn("Hành động").setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            JButton editButton = new JButton("Sửa");
            JButton deleteButton = new JButton("Xoá");
            panel.add(editButton);
            panel.add(deleteButton);
            return panel;
        });

        JScrollPane scrollPane = new JScrollPane(supplierTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addEditButtonListener(ActionListener listener) {

    }

    public void addDeleteButtonListener(ActionListener listener) {

    }

    public JTable getSupplierTable() {
        return supplierTable;
    }
}
