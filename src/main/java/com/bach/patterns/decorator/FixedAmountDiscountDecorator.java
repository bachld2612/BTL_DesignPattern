package com.bach.patterns.decorator;

public class FixedAmountDiscountDecorator extends ProductDecorator{
    private float discountAmount;

    public FixedAmountDiscountDecorator(ProductComponent product, float discountAmount) {
        super(product);
        this.discountAmount = discountAmount;
    }

    @Override
    public float getPrice() {
        float originalPrice = super.getPrice();
        float discounted = originalPrice - discountAmount;
        return Math.max(0, discounted); // không cho âm giá
    }
}
