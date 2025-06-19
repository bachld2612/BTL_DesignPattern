package com.bach.view;

import com.bach.component.Navbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
<<<<<<< HEAD
    private Navbar navbar;

    public LoginView() {
        setTitle("Login");
        setSize(300, 300);
=======


    public LoginView() {
        setTitle("Login");
        setSize(500, 300);
>>>>>>> 5c2465b58224fa6a7df3b0611b67b21879cf4efa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(false); // Prevent resizing
<<<<<<< HEAD
        navbar = new Navbar(this);
        navbar.setVisible(true);
        navbar.setLocation(0, 0);
        add(navbar, BorderLayout.NORTH);


=======
>>>>>>> 5c2465b58224fa6a7df3b0611b67b21879cf4efa

        JPanel panel = new JPanel();
        panel.setLayout(null);
        // Set panel bounds below the navbar (assuming navbar height is 50)
        panel.setBounds(0, 50, 500, 450);

<<<<<<< HEAD
        JLabel usernameLabel = new JLabel("Username:");
=======
        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
>>>>>>> 5c2465b58224fa6a7df3b0611b67b21879cf4efa
        usernameLabel.setBounds(10, 20, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

<<<<<<< HEAD
        JLabel passwordLabel = new JLabel("Password:");
=======
        JLabel passwordLabel = new JLabel("Mật khẩu:");
>>>>>>> 5c2465b58224fa6a7df3b0611b67b21879cf4efa
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

<<<<<<< HEAD
        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 125, 25); // Set same width
        panel.add(loginButton);

        registerButton = new JButton("Register");
=======
        loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(10, 80, 125, 25); // Set same width
        panel.add(loginButton);

        registerButton = new JButton("Đăng ký");
>>>>>>> 5c2465b58224fa6a7df3b0611b67b21879cf4efa
        registerButton.setBounds(140, 80, 125, 25); // Set same width
        panel.add(registerButton);

        add(panel);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
