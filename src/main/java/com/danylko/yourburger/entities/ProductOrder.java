package com.danylko.yourburger.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "product_list")
public class ProductOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROD_LIST_ID")
    @JsonIgnore
    private Long prodListId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "COUNT")
    private int count;

}
