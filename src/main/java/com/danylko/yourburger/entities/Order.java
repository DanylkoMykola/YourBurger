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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    /*@JoinTable(name = "product_list",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "NAME")
    )*/
    private Set<ProductOrder> productOrderSet = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FAC_ID")
    private Facility facility;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public Order() {
    }

    public Order(Set<ProductOrder> productOrderSet, Facility facility, Customer customer, Address address, int totalPrice) {
        this.productOrderSet = productOrderSet;
        this.facility = facility;
        this.customer = customer;
        this.address = address;
        this.totalPrice = totalPrice;
    }

    public Set<ProductOrder> getProductOrders() {
        return productOrderSet;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Set<ProductOrder> getProductOrderSet() {
        return productOrderSet;
    }

    public void setProductOrderSet(Set<ProductOrder> productOrderSet) {
        this.productOrderSet = productOrderSet;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", productOrderSet=" + productOrderSet +
                ", facility=" + facility +
                ", customer=" + customer +
                ", address=" + address +
                ", price=" + totalPrice +
                ", orderDate=" + orderDate +
                ", version=" + version +
                '}';
    }
}
