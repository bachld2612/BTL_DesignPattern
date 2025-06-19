package com.bach.controller;

import com.bach.patterns.userbuilder.CustomerBuilder;
import com.bach.patterns.userbuilder.UserBuilder;
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

    public void register(){
        if(registerView.getUsername().isEmpty() ||
                registerView.getPassword().isEmpty() ||
                registerView.getFullName().isEmpty() ||
                registerView.getPhone().isEmpty() ||
                registerView.getAddress().isEmpty() ||
                registerView.getDateOfBirth() == null) {
            registerView.showError("All fields are required");
            return;
        }
        try {
            CustomerBuilder builder = new CustomerBuilder();
            UserDirector director = new UserDirector();
            director.createCustomerFromRegisterView(builder, registerView);
            userService.register(builder.getResult());
            registerView.showMessage("Registration successful, please login to use our services");
        } catch (RuntimeException e) {
            registerView.showError(e.getMessage());
        }
    }

    public void backToLogin(){
        registerView.dispose();
        new LoginController();
    }

}
