package com.danylko.yourburger.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name = "burgers")
public class Burger implements Serializable {

    private int burid;
    private String name;
    private byte[] image;
    private String description;
    private int price;
    private int version;

    public void setBurid(int burid) {
        this.burid = burid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BURID")
    public int getBurid() {
        return this.burid;
    }

    @Version
    @Column(name = "VERSION")
    public int getVersion() {
        return this.version;
    }

    @Column(name = "IMAGE")
    public byte[] getImage() {
        return this.image;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return this.description;
    }

    @Column(name = "PRICE")
    public int getPrice() {
        return this.price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Burger{" +
                "burid=" + burid +
                ", name='" + name + '\'' +
                ", image=" + Arrays.toString(image) +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", version=" + version +
                '}';
    }
}
