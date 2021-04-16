package com.danylko.yourburger.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
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

}