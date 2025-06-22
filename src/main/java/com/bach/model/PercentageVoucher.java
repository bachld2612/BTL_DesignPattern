package com.bach.model;

import com.bach.patterns.strategy.PercentageDiscountStrategy;
import com.bach.patterns.strategy.DiscountContext;

public class PercentageVoucher implements IVoucher {
    private int id;
    private String code;
    private String name;
    private double startValue;
    private double endValue;
    private boolean isActive;
    private double percentage;
    private DiscountContext discountContext;

    public PercentageVoucher(int id, String code, String name, double startValue, double endValue, boolean isActive, double percentage) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.startValue = startValue;
        this.endValue = endValue;
        this.isActive = isActive;
        this.percentage = percentage;
        this.discountContext = new DiscountContext();
        this.discountContext.setStrategy(new PercentageDiscountStrategy(percentage));
    }

    @Override
    public double calculateDiscount(double originalPrice) {
        if (!isActive || originalPrice < startValue || originalPrice > endValue) {
            return 0;
        }
        return discountContext.calculateDiscount(originalPrice);
    }

    public double getPercentage() { return percentage; }

    @Override
    public String getDiscountDescription() {
        return String.format("%.0f%% off", percentage);
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