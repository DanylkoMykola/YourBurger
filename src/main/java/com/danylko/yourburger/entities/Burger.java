package com.danylko.yourburger.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "burgers")
public class Burger implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BUR_ID")
    private int burId;

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

    @ManyToMany
    @JoinTable(name = "product_list",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "BUR_ID")
    )
    private Set<Order> orders = new HashSet<>();

    public int getBurId() {
        return this.burId;
    }

    public String getName() { return this.name; }

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

    public Set<Order> getOrders() {
        return this.orders;
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

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Burger{" +
                "burid=" + burId +
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
        return burId == burger.burId &&
                Objects.equals(name, burger.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(burId, name);
    }
}
