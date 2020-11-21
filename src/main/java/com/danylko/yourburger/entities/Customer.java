package com.danylko.yourburger.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUST_ID")
    private int custId;

    @Column(name = "FIRST_NAME")
    private String fristname;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "RATING")
    private int rating;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    @OneToOne(mappedBy = "customer")
    private Order order;

    public int getCustId() {
        return custId;
    }

    public String getFristname() {
        return fristname;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRating() {
        return rating;
    }

    public Order getOrder() { return this.order; }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public void setFristname(String fristname) {
        this.fristname = fristname;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setOrder(Order order) { this.order = order; }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public boolean addAddress(Address address) {
        address.setCustomer(this);
        return  getAddresses().add(address);
    }

    public void removeAddress(Address address) {
        getAddresses().remove(address);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custid=" + custId +
                ", fristname='" + fristname + '\'' +
                ", surname='" + lastName + '\'' +
                ", phonenumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rating=" + rating +
                ", addresses=" + addresses +
                '}';
    }
}
