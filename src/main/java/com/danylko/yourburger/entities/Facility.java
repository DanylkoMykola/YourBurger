package com.danylko.yourburger.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

public class Facility implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FACID")
    private int facid;

    @Column(name = "FACCITY")
    private String faccity;

    @Column(name = "SERVINGCITY")
    private String servingcity;

    public int getFacid() {
        return facid;
    }

    public String getFaccity() {
        return faccity;
    }

    public String getServingcity() {
        return servingcity;
    }

    public void setFaccity(String faccity) {
        this.faccity = faccity;
    }

    public void setServingcity(String servingcity) {
        this.servingcity = servingcity;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "facid=" + facid +
                ", faccity='" + faccity + '\'' +
                ", servingcity='" + servingcity + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facility facility = (Facility) o;
        return Objects.equals(faccity, facility.faccity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faccity);
    }
}
