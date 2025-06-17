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

    public void login(){
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        try {
            userService.login(username, password);

            // Kiểm tra nếu là admin thì mở giao diện giảm giá
            if ("admin".equals(com.bach.patterns.sessionsingleton.Session.getInstance().getRole())) {
                loginView.dispose(); // Đóng cửa sổ đăng nhập
                new AdminDiscountView().setVisible(true);
            } else {
                loginView.showMessage("Bạn đã đăng nhập thành công với vai trò khách hàng.");
                // TODO: mở giao diện khác cho customer nếu muốn
            }

        } catch (RuntimeException ex) {
            loginView.showError(ex.getMessage());
        }
    }

    public void backToRegister(){
        loginView.dispose();
        new RegisterController();
    }

}
