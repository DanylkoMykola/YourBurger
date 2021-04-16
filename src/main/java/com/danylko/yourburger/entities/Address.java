package com.danylko.yourburger.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
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

}