package com.danylko.yourburger.entities;

public class ProductOrder {

    private String name;
    private int price;
    private int count;

    public ProductOrder() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + count +
                '}';
    }
}
