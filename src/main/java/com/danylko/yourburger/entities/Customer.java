package com.danylko.yourburger.entities;


import com.danylko.yourburger.enums.Role;
import com.danylko.yourburger.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUST_ID")
    private Long custId;

    @Column(name = "FIRST_NAME", length = 40)
    private String firstName;

    @Column(name = "LAST_NAME", length = 40)
    private String lastName;

    @Column(name = "PHONE_NUMBER", length = 25, unique = true)
    private String phoneNumber;

    @Email
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ROLE")
    private Role role = Role.CUSTOMER;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "STATUS")
    private Status status = Status.ACTIVE;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

}