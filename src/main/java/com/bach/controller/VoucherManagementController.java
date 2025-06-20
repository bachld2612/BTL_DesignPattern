package com.bach.controller;

import com.bach.view.VoucherManagementView;

public class VoucherManagementController {
    private VoucherManagementView view;

    public VoucherManagementController() {
        view = new VoucherManagementView();
    }

    public void showView() {
        view.setVisible(true);
    }

    public void hideView() {
        view.setVisible(false);
    }
} 