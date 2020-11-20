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
    @Column(name = "CUSTID")
    private int custid;

    @Column(name = "FIRSTNAME")
    private String fristname;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "PHONENUMBER")
    private String phonenumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "RATING")
    private int rating;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    public int getCustid() {
        return this.custid;
    }

    public String getFristname() {
        return this.fristname;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public int getRating() {
        return this.rating;
    }

    public Set<Address> getAddresses() {
        return this.addresses;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public void setFristname(String fristname) {
        this.fristname = fristname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

}
