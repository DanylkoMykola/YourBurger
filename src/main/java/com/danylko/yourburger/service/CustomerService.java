package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Customer;

import java.util.Set;

public interface CustomerService {
    Set<Customer> findAll();
    Customer findById(Long id);
    Customer findByPhoneNumber(String phoneNumber);
    void save(Customer customer);
    void delete(Customer customer);
    void checkIfNewCustomer(Customer customer);
}
