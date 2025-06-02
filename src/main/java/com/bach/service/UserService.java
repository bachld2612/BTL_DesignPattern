package com.bach.service;

import com.bach.dao.user.UserDAO;
import com.bach.model.User;

import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public List<User> getAllUsers() {
        return userDAO.getUsers();
    }

}
