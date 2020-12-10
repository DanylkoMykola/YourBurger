package com.danylko.yourburger.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private int orderId;

    @ManyToMany
    @JoinTable(name = "product_list",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PROD_ID")
    )
    private Set<Product> products = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FAC_ID")
    private Facility facility;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUST_ID")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Column(name = "TOTAL_PRICE")
    private int totalPrice;

    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @Version
    @Column(name = "VERSION")
    private int version;

    public Set<Product> getProducts() {
        return products;
    }

    public Facility getFacility() {
        return facility;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Address getAddress() {
        return address;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getVersion() {
        return version;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", products=" + products +
                ", facility=" + facility +
                ", customer=" + customer +
                ", address=" + address +
                ", price=" + totalPrice +
                ", orderDate=" + orderDate +
                ", version=" + version +
                '}';
    }
}
