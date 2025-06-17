package com.bach.patterns.decorator;

public class DiscountDecorator extends  ProductDecorator{
    private float discountPercent; // ví dụ: 20 nghĩa là 20%

    public DiscountDecorator(ProductComponent product, float discountPercent) {
        super(product);
        this.discountPercent = discountPercent;
    }

    @Override
    public float getPrice() {
        return product.getPrice() * (1 - discountPercent / 100f);
    }
}
