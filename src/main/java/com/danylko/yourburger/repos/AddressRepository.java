package com.danylko.yourburger.repos;

import com.danylko.yourburger.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {

    Address findByCity(String city);
    Address findByStreet(String street);
}
