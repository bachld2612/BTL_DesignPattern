package com.bach.controller;

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

            String role = com.bach.patterns.sessionsingleton.Session.getInstance().getRole();

            if ("admin".equalsIgnoreCase(role)) {
                loginView.dispose();
                new AdminDiscountView().setVisible(true);
            } else if ("customer".equalsIgnoreCase(role)) {
                loginView.showMessage("Bạn đã đăng nhập thành công với vai trò khách hàng.");
                // TODO: chuyển sang giao diện chính của customer nếu cần
            } else {
                loginView.showMessage("Đăng nhập thành công!");
            }

        } catch (RuntimeException ex) {
            // E-3: Sai tài khoản hoặc mật khẩu
            loginView.showError("Tên đăng nhập hoặc mật khẩu không chính xác. Vui lòng kiểm tra lại");
        }
    }


    public void backToRegister(){
        loginView.dispose();
        new RegisterController();
    }

}
