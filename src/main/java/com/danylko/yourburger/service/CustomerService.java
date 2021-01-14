package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Integer id);
    void save(Customer customer);
    void delete(Customer customer);
}
