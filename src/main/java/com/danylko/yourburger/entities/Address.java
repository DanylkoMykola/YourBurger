package com.danylko.yourburger.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private int addressId;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "STREET_NUMBER")
    private String streetNumber;

    @Column(name = "APARTMENT")
    private String apartment;

    @ManyToOne
    @JoinColumn(name = "CUST_ID")
    private Customer customer;

    @OneToOne(mappedBy = "address")
    private Order order;

    public int getAddressId() {
        return addressId;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getApartment() {
        return apartment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order getOrder() { return this.order; }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrder(Order order) { this.order = order; }

    @Override
    public String toString() {
        return "Address{" +
                "addressid=" + addressId +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetnumber='" + streetNumber + '\'' +
                ", apartment='" + apartment + '\'' +
                ", customer=" + customer +
                '}';
    }
}
