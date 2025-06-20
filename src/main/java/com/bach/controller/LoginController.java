package com.bach.controller;

import com.bach.controller.supplier.CreateSupplierController;
import com.bach.controller.supplier.SupplierController;
import com.bach.patterns.sessionsingleton.Session;
import com.bach.service.UserService;
import com.bach.view.LoginView;
import com.bach.view.AdminDiscountView;

public class LoginController {

    private final UserService userService;
    private final LoginView loginView;

    public LoginController() {
        this.userService = new UserService();
        this.loginView = new LoginView();
        this.loginView.setVisible(true);
        initListeners();
    }

    private void initListeners() {
        loginView.addLoginListener(e -> login());
        loginView.addRegisterListener(e -> backToRegister());
    }

    public void login() {
        String username = loginView.getUsername().trim();
        String password = loginView.getPassword().trim();

        // E-1: Chưa nhập username
        if (username.isEmpty()) {
            loginView.showError("Vui lòng nhập tài khoản");
            return;
        }

        // E-2: Chưa nhập password
        if (password.isEmpty()) {
            loginView.showError("Vui lòng nhập mật khẩu");
            return;
        }

        try {
            // Thử đăng nhập (có thể ném RuntimeException nếu sai)
            userService.login(username, password);
            loginView.showMessage("Login successful with role: " + Session.getInstance().getRole() + " id: " + Session.getInstance().getId());
            if (Session.getInstance().getRole().equals("admin")) {
                new SupplierController();
                loginView.dispose();
            }
            else{
                new CustomerMainController();
                loginView.dispose();
            }
        }catch (RuntimeException e) {
            loginView.showError(e.getMessage());
        }
    }


    public void backToRegister(){
        loginView.dispose();
        new RegisterController();
    }

}
