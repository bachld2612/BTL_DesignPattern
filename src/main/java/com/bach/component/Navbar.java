package com.bach.component;

import com.bach.util.Navigator;

import javax.swing.*;
import java.awt.*;

public class Navbar extends JPanel {

    public Navbar(JFrame currentFrame) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(currentFrame.getWidth(), 40));
        setVisible(true);
        add(new DivComponent(NavItem.LOGIN.getTitle(), () -> Navigator.navigate(NavItem.LOGIN, currentFrame)));
        add(new DivComponent(NavItem.REGISTER.getTitle(), () -> Navigator.navigate(NavItem.REGISTER, currentFrame)));

        JButton invoiceButton = new JButton("Hóa Đơn");

        JPopupMenu invoiceMenu = new JPopupMenu();
        JMenuItem createInvoice = new JMenuItem("Hoá Đơn Nhập Hàng");
        JMenuItem listInvoice = new JMenuItem("Hoá Đơn Bán Hàng");

        createInvoice.addActionListener(e -> Navigator.navigate( NavItem.INVOICEPURCHASE,currentFrame));
        listInvoice.addActionListener(e -> Navigator.navigate(NavItem.INVOICESALE,currentFrame));

        invoiceMenu.add(createInvoice);
        invoiceMenu.add(listInvoice);

        invoiceButton.addActionListener(e -> invoiceMenu.show(invoiceButton, 0, invoiceButton.getHeight()));

        add(invoiceButton);
    }

    public enum NavItem{
        LOGIN("Đăng nhập"),
        REGISTER("Đăng ký"),
        EXIT("Thoát"),
        INVOICESALE(""),
        INVOICEPURCHASE("")
        ;
        private final String title;

        NavItem(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

}
