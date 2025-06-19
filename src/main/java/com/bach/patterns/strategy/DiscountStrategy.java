package com.bach.patterns.strategy;

public interface DiscountStrategy {
    double calculateDiscount(double amount);
    String getDescription();
    String getType();
    double getValue();
}