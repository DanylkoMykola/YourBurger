package com.danylko.yourburger.entities;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrder> productOrderList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FAC_ID")
    private Facility facility;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUST_ID")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Column(name = "TOTAL_PRICE")
    private int totalPrice;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @Version
    @Column(name = "VERSION")
    private int version;

    @Transient
    private String jsonOrderlist;

}