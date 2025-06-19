package com.bach.patterns.state;

import com.bach.model.Order;

public class PaidOrderState extends OrderState {
    @Override
    public void ship(Order order) {
        order.setState(new ShippedOrderState());
        order.setStatus("SHIPPED");
    }

    @Override
    public void cancel(Order order) {
        order.setState(new CanceledOrderState());
        order.setStatus("CANCELED");
    }

    @Override
    public String getStateName() {
        return "PAID";
    }
} 