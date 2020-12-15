package com.danylko.yourburger.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "facilities")
public class Facility implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FAC_ID")
    private int facId;

    @Column(name = "FAC_CITY", length = 40)
    private String facCity;

    @Column(name = "SERVING_CITY", length = 40)
    private String servingCity;

    @OneToOne(mappedBy = "facility")
    private Order order;

    public int getFacId() {
        return facId;
    }

    public String getFacCity() {
        return facCity;
    }

    public String getServingCity() {
        return servingCity;
    }

    public Order getOrder() {
        return order;
    }

    public void setFacCity(String facCity) {
        this.facCity = facCity;
    }

    public void setServingCity(String servingCity) {
        this.servingCity = servingCity;
    }

    public void setOrder(Order order) {
        this.order = order;
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
