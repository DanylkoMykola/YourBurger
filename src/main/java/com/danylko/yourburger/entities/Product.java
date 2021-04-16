package com.danylko.yourburger.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROD_ID")
    private Long prodId;

    @EqualsAndHashCode.Include
    @Column(name = "NAME",length = 60)
    private String name;

    @Column(name = "IMAGE", length = 50)
    private String image;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Column(name = "PRICE")
    private int price;

    @Version
    @Column(name = "VERSION")
    private int version;


    public Product() {
    }

    public Product(String name, String image, String description, int price) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    public Product(Long prodId, String name, String image, String description, int price) {
        this.prodId = prodId;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
    }
}
