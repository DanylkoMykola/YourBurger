package com.danylko.yourburger.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @Column(name = "CITY", length = 40)
    private String city;

    @Column(name = "STREET", length = 100)
    private String street;

    @Column(name = "STREET_NUMBER", length = 15)
    private String streetNumber;

    @Column(name = "APARTMENT_NUMBER", length = 5)
    private String apartment;


    @OneToOne(mappedBy = "address")
    private Order order;

    public Address() {
    }

    public Address(String city,
                   String street,
                   String streetNumber,
                   String apartment) {
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartment = apartment;
    }

    public Address(String city,
                   String street,
                   String streetNumber,
                   String apartment,
                   Order order) {
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartment = apartment;
        this.order = order;
    }

    public Long getAddressId() {
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

    public Order getOrder() { return this.order; }

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


    public void setOrder(Order order) { this.order = order; }


    @Override
    public String toString() {
        return "Address{" +
                "addressid=" + addressId +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", streetnumber='" + streetNumber + '\'' +
                ", apartment='" + apartment + '\'' +
               // ", customer=" + customer +
                '}';
    }
}
