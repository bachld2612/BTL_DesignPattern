package com.bach.patterns.state;

import com.bach.model.Order;

public class ShippedOrderState implements OrderState {
    @Override
    public void pay(Order order) {
        throw new IllegalStateException("Order is already paid and shipped.");
    }

    @Override
    public void ship(Order order) {
        throw new IllegalStateException("Order is already shipped.");
    }

    @Override
    public void complete(Order order) {
        order.setState(new CompletedOrderState());
        order.setStatus("COMPLETED");
    }

    @Override
    public void cancel(Order order) {
        throw new IllegalStateException("Cannot cancel a shipped order.");
    }

    @Override
    public String getStateName() {
        return "SHIPPED";
    }
} 