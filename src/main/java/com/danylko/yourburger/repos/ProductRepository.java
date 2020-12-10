package com.danylko.yourburger.repos;

import com.danylko.yourburger.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
