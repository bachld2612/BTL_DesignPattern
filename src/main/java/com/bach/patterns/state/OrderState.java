package com.bach.patterns.state;

import com.bach.model.Order;

public interface OrderState {
    void pay(Order order);
    void ship(Order order);
    void complete(Order order);
    void cancel(Order order);
    String getStateName();
} 