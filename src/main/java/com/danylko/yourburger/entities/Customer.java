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
        return custid;
    }

    public String getFristname() {
        return fristname;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhonenumber() {
        return phonenumber;
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

    public Set<Address> getAddresses() {
        return addresses;
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

    @Override
    public String toString() {
        return "Customer{" +
                "custid=" + custid +
                ", fristname='" + fristname + '\'' +
                ", surname='" + surname + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rating=" + rating +
                ", addresses=" + addresses +
                '}';
    }
}
