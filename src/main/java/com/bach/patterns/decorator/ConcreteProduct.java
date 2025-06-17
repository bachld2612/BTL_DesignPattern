package com.bach.patterns.decorator;

public class ConcreteProduct implements ProductComponent{
    private String name;
    private float price;

    public ConcreteProduct(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getPrice() {
        return price;
    }
}
