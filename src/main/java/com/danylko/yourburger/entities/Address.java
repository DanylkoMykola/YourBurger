package com.danylko.yourburger.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

    @ManyToOne
    @JoinColumn(name = "CUSTID")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
