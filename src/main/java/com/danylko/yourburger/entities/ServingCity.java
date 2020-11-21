package com.danylko.yourburger.entities;

import javax.persistence.*;
import java.io.Serializable;

public class ServingCity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERVCITYID")
    private int servcityid;

    @Column(name = "CITY")
    private String city;

    public int getServcityid() {
        return servcityid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ServingCity{" +
                "servcityid=" + servcityid +
                ", city='" + city + '\'' +
                '}';
    }
}
