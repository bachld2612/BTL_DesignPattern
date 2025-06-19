package com.bach.patterns.state;

public class GoldLevel implements MemberLevel {
    @Override
    public String getLevelName() {
        return "Gold";
    }

    @Override
    public double getDiscountRate() {
        return 0.15; // 15% discount
    }

    @Override
    public int getRequiredPoints() {
        return 5000;
    }

    @Override
    public String getDescription() {
        return "VIP member level with 15% discount";
    }
} 