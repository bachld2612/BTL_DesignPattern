package com.bach.patterns.state;

import com.bach.model.Order;

public class CanceledOrderState implements OrderState {
    @Override
    public void pay(Order order) {
        throw new IllegalStateException("Order is canceled.");
    }

    @Override
    public void ship(Order order) {
        throw new IllegalStateException("Order is canceled.");
    }

    @Override
    public void complete(Order order) {
        throw new IllegalStateException("Order is canceled.");
    }

    @Override
    public void cancel(Order order) {
        throw new IllegalStateException("Order is canceled.");
    }

    @Override
    public String getStateName() {
        return "CANCELED";
    }
} 