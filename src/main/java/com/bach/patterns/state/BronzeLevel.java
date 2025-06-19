package com.bach.patterns.state;

public class BronzeLevel implements MemberLevel {
    @Override
    public String getLevelName() {
        return "Bronze";
    }

    @Override
    public double getDiscountRate() {
        return 0.05; // 5% discount
    }

    @Override
    public int getRequiredPoints() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Basic member level with 5% discount";
    }
} 