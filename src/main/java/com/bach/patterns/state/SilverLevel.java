package com.bach.patterns.state;

public class SilverLevel implements MemberLevel {
    @Override
    public String getLevelName() {
        return "Silver";
    }

    @Override
    public double getDiscountRate() {
        return 0.10; // 10% discount
    }

    @Override
    public int getRequiredPoints() {
        return 1000;
    }

    @Override
    public String getDescription() {
        return "Premium member level with 10% discount";
    }
} 