package com.danylko.yourburger.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROD_ID")
    private Long prodId;

    @Column(name = "NAME",length = 60)
    @Size
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
   /* @ManyToMany
    @JoinTable(name = "product_list",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "NAME")
    )*/
    private Set<ProductOrder> productOrderSet = new HashSet<>();

    public Product() {
    }

    public Product(String name, String image, String description, int price) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    public Product(Long prodId , String name, String image, String description, int price) {
        this.prodId = prodId;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
    }

    public Long getProdId() {
        return this.prodId;
    }

    public String getName() { return this.name; }

    public int getVersion() {
        return this.version;
    }

    public String getImage() {
        return this.image;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public Set<ProductOrder> getProductOrders() {
        return this.productOrderSet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
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

    public void setOrders(Set<ProductOrder> productOrderSet) {
        this.productOrderSet = productOrderSet;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prod_id=" + prodId +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return prodId == product.prodId &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prodId, name);
    }
}
