package com.danylko.yourburger.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "facilities")
public class Facility implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FAC_ID")
    private Long facId;

    @Column(name = "FAC_CITY", length = 40)
    private String facCity;

    @Column(name = "SERVING_CITY", length = 40)
    private String servingCity;

    @OneToMany(mappedBy = "facility")
    private List<Order> orders;

    public Long getFacId() {
        return facId;
    }

    public String getFacCity() {
        return facCity;
    }

    public String getServingCity() {
        return servingCity;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setFacCity(String facCity) {
        this.facCity = facCity;
    }

    public void setServingCity(String servingCity) {
        this.servingCity = servingCity;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setFacId(Long facId) {
        this.facId = facId;
    }
    public Facility() {
    }



    public Facility(String facCity, String servingCity, List<Order> orders) {
        this.facCity = facCity;
        this.servingCity = servingCity;
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "facid=" + facId +
                ", faccity='" + facCity + '\'' +
                ", servingcity='" + servingCity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facility facility = (Facility) o;
        return Objects.equals(facCity, facility.facCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facCity);
    }
}
