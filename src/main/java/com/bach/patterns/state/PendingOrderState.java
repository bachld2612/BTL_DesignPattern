package com.bach.patterns.state;

import com.bach.model.Order;

public class PendingOrderState implements OrderState {
    @Override
    public void pay(Order order) {
        order.setState(new PaidOrderState());
        order.setStatus("PAID");
    }

    @Override
    public void ship(Order order) {
        throw new IllegalStateException("Cannot ship a pending order.");
    }

    @Override
    public void complete(Order order) {
        throw new IllegalStateException("Cannot complete a pending order.");
    }

    @Override
    public void cancel(Order order) {
        order.setState(new CanceledOrderState());
        order.setStatus("CANCELED");
    }

    @Override
    public String getStateName() {
        return "PENDING";
    }
} 