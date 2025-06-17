package com.bach.patterns.decorator;

public abstract class ProductDecorator implements ProductComponent{
    protected ProductComponent product;

    public ProductDecorator(ProductComponent product) {
        this.product = product;
    }

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public float getPrice() {
        return product.getPrice();
    }

}
