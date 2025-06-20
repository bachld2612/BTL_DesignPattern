package com.bach.model;

import com.bach.patterns.strategy.FixedAmountDiscountStrategy;

public class FixedAmountVoucher implements IVoucher {
    private int id;
    private String code;
    private String name;
    private double startValue;
    private double endValue;
    private boolean isActive;
    private double amount;

    public FixedAmountVoucher(int id, String code, String name, double startValue, double endValue, boolean isActive, double amount) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.startValue = startValue;
        this.endValue = endValue;
        this.isActive = isActive;
        this.amount = amount;
    }

    @Override
    public double calculateDiscount(double originalPrice) {
        if (!isActive || originalPrice < startValue || originalPrice > endValue) {
            return 0;
        }
        return new FixedAmountDiscountStrategy(amount).calculateDiscount(originalPrice);
    }

    public double getAmount() { return amount; }

    @Override
    public String getDiscountDescription() {
        return String.format("-%,.0f VND", amount);
    }

    @Override
    public int getId() { return id; }
    @Override
    public String getCode() { return code; }
    @Override
    public String getName() { return name; }
    @Override
    public double getStartValue() { return startValue; }
    @Override
    public double getEndValue() { return endValue; }
    @Override
    public boolean isActive() { return isActive; }
} 