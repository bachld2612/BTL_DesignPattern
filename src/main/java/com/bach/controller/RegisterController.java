package com.bach.controller;

import com.bach.patterns.userbuilder.CustomerBuilder;
import com.bach.patterns.userbuilder.UserDirector;
import com.bach.service.UserService;
import com.bach.view.RegisterView;

public class RegisterController {

    private final UserService userService;
    private final RegisterView registerView;

    public RegisterController() {
        this.userService = new UserService();
        this.registerView = new RegisterView();
        this.registerView.setVisible(true);
        initListeners();
    }

    public void initListeners() {
        registerView.addRegisterListener(e -> register());
        registerView.addLoginListener(e -> backToLogin());
    }

    public void register() {
        if (!registerView.isValidInput()) {
            return; // Dừng lại nếu dữ liệu không hợp lệ
        }


        try {
            CustomerBuilder builder = new CustomerBuilder();
            UserDirector director = new UserDirector();
            director.createCustomerFromRegisterView(builder, registerView);
            userService.register(builder.getResult());

            registerView.showMessage("Đăng ký thành công! Vui lòng đăng nhập để sử dụng dịch vụ.");
            registerView.dispose();
            new LoginController();
        } catch (RuntimeException e) {
            registerView.showError("Lỗi: " + e.getMessage());
        }
    }

    public void backToLogin() {
        registerView.dispose();
        new LoginController();
    }
}
