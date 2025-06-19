package com.bach.controller;

import com.bach.service.OrderService;
import com.bach.view.OrderView;
import javax.swing.JOptionPane;


public class OrderController {
    private OrderView orderView;
    private OrderService orderService;

    public OrderController() {
        orderService = OrderService.getInstance();
        orderView = new OrderView();
        // Đăng ký listener để nhận sự kiện đặt hàng từ View
        orderView.setOrderListener((paymentMethod, note, cartId, voucherIndex, finalAmount) -> {
            handleOrder(paymentMethod, note, cartId, voucherIndex, finalAmount);
        });
        orderView.setVisible(true);
//        initializeListeners();
    }

//    private void initializeListeners() {
//
//    }

    public void showOrderView() {
        orderView.setVisible(true);
    }

    public void hideOrderView() {
        orderView.setVisible(false);
    }

    public OrderView getOrderView() {
        return orderView;
    }

    // Thêm hàm xử lý logic đặt hàng
    private void handleOrder(String paymentMethod, String note, int cartId, int voucherIndex, double finalAmount) {
        int customerId = orderView.getCustomerId();
        orderService.createOrder(customerId, cartId, finalAmount, paymentMethod, note);
        // Áp dụng voucher nếu có
        if (voucherIndex > 0) {
            java.util.List<com.bach.model.IVoucher> vouchers = orderService.getAvailableVouchers();
            if (voucherIndex - 1 < vouchers.size()) {
                com.bach.model.IVoucher selectedVoucher = vouchers.get(voucherIndex - 1);
                orderService.applyVoucherToOrder(cartId, selectedVoucher.getId());
            }
        }
        JOptionPane.showMessageDialog(orderView, "Đặt hàng thành công!");
        orderView.reloadData();
    }
} 