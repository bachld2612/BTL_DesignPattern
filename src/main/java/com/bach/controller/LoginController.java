package com.bach.controller;

import com.bach.patterns.sessionsingleton.Session;
import com.bach.service.UserService;
import com.bach.view.LoginView;

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
        try{
            userService.login(username, password);
            loginView.showMessage("Login successful with role: " + Session.getInstance().getRole() + " id: " + Session.getInstance().getId());
            if (Session.getInstance().getRole().equals("admin")) {
//                new LoginController();
            }
            else{
                new CustomerMainController();
                loginView.setVisible(false);
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
