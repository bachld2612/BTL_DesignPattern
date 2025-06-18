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
        add(new DivComponent(NavItem.SUPPLIER.getTitle(), () -> Navigator.navigate(NavItem.SUPPLIER, currentFrame)));
    }

    public enum NavItem{
        LOGIN("Đăng nhập"),
        REGISTER("Đăng ký"),
        EXIT("Thoát"),
        SUPPLIER("Nhà cung cấp"),
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
