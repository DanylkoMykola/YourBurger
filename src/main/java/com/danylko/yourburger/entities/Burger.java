package com.danylko.yourburger.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "burgers")
public class Burger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BURID")
    private int burid;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IMAGE")
    private byte[] image;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private int price;

    @Version
    @Column(name = "VERSION")
    private int version;

    public int getBurid() {
        return this.burid;
    }

    public int getVersion() {
        return this.version;
    }

    public byte[] getImage() {
        return this.image;
    }

    public String getDescription() {
        return this.description;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Burger burger = (Burger) o;
        return burid == burger.burid &&
                Objects.equals(name, burger.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(burid, name);
    }
}
