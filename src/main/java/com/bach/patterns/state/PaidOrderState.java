package com.bach.patterns.state;

import com.bach.model.Order;

public class PaidOrderState implements OrderState {
    @Override
    public void pay(Order order) {
        throw new IllegalStateException("Order is already paid.");
    }

    @Override
    public void ship(Order order) {
        order.setState(new ShippedOrderState());
        order.setStatus("SHIPPED");
    }

    @Override
    public void complete(Order order) {
        throw new IllegalStateException("Cannot complete an order before shipping.");
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