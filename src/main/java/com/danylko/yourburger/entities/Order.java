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
            inverseJoinColumns = @JoinColumn(name = "BUR_ID")
    )
    private Set<Burger> burgers = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FAC_ID")
    private Facility facility;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUST_ID")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Column(name = "PRICE")
    private int price;

    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @Version
    @Column(name = "VERSION")
    private int version;

    public Set<Burger> getBurgers() {
        return burgers;
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

    public int getPrice() {
        return price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getVersion() {
        return version;
    }

    public void setBurgers(Set<Burger> burgers) {
        this.burgers = burgers;
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

    public void setPrice(int price) {
        this.price = price;
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
                ", burgers=" + burgers +
                ", facility=" + facility +
                ", customer=" + customer +
                ", address=" + address +
                ", price=" + price +
                ", orderDate=" + orderDate +
                ", version=" + version +
                '}';
    }
}
