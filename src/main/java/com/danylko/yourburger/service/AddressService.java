package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Address;
import com.danylko.yourburger.repos.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface AddressService {

    Set<Address> findAll();
    Address findById(Long id);
    Address findByCity(String city);
    Address findByStreet(String street);
    Address findByCustomerId(Long id);
    void save(Address address);
    void delete(Address address);
}
