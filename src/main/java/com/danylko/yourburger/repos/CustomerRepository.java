package com.danylko.yourburger.repos;

import com.danylko.yourburger.entities.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
