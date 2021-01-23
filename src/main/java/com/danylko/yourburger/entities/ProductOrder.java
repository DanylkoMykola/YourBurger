package com.danylko.yourburger.entities;

import javax.persistence.*;

@Entity
@Table(name = "product_list")
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROD_LIST_ID")
    private Long prodListId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "COUNT")
    private int count;

    @ManyToOne
    @JoinColumn(name = "PROD_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

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

    public Long getProdListId() {
        return prodListId;
    }

    public void setProdListId(Long prodListId) {
        this.prodListId = prodListId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return "ProductOrder{" +
                ", price=" + price +
                ", amount=" + count +
                '}';
    }
}
