package com.bach.component;

import com.bach.util.Navigator;

import javax.swing.*;
import java.awt.*;

public class Navbar extends JPanel {

    public Navbar(JFrame currentFrame) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.WHITE);
        int width = (currentFrame != null) ? currentFrame.getWidth() : 900;
        setPreferredSize(new Dimension(width, 40));
        setVisible(true);

        add(new DivComponent(NavItem.LOGIN.getTitle(), () -> Navigator.navigate(NavItem.LOGIN, currentFrame)));
        add(new DivComponent(NavItem.REGISTER.getTitle(), () -> Navigator.navigate(NavItem.REGISTER, currentFrame)));
        add(new DivComponent(NavItem.ORDER.getTitle(), () -> Navigator.navigate(NavItem.ORDER, currentFrame)));
        add(new DivComponent(NavItem.VOUCHER.getTitle(), () -> Navigator.navigate(NavItem.VOUCHER, currentFrame)));
        add(new DivComponent(NavItem.EXIT.getTitle(), () -> Navigator.navigate(NavItem.EXIT, currentFrame)));
    }

    public enum NavItem{
        LOGIN("Đăng nhập"),
        REGISTER("Đăng ký"),
        ORDER("Đặt hàng"),
        VOUCHER("Quản lý voucher"),
        EXIT("Thoát");
        private final String title;

        NavItem(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}