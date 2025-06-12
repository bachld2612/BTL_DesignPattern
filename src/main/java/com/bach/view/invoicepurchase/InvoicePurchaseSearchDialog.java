package com.bach.view.invoicepurchase;

import javax.swing.*;
import java.awt.*;

public class InvoicePurchaseSearchDialog extends JDialog{
    private final JTextField txtSearchId;
    private final JButton btnSearch;
    private final JTextArea resultArea;

    public InvoicePurchaseSearchDialog(JFrame parent) {
        super(parent, "Tìm kiếm hóa đơn", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Nhập ID hóa đơn:"));
        txtSearchId = new JTextField(10);
        topPanel.add(txtSearchId);
        btnSearch = new JButton("Tìm kiếm");
        topPanel.add(btnSearch);
        add(topPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    public JTextField getTxtSearchId() {
        return txtSearchId;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public void displayResult(String result) {
        resultArea.setText(result);
    }
}
