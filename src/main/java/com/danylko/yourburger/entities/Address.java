package com.danylko.yourburger.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESSID")
    private int addressid;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "STREETNUMBER")
    private String streetnumber;

    @Column(name = "APARTMENT")
    private String apartment;

    @ManyToOne
    @JoinColumn(name = "CUSTID")
    private Customer customer;

    public int getAddressid() {
        return addressid;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetnumber() {
        return streetnumber;
    }

    public String getApartment() {
        return apartment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setAddressid(int addressid) {
        this.addressid = addressid;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetnumber(String streetnumber) {
        this.streetnumber = streetnumber;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressid=" + addressid +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetnumber='" + streetnumber + '\'' +
                ", apartment='" + apartment + '\'' +
                ", customer=" + customer +
                '}';
    }
}
