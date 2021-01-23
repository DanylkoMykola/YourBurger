package com.danylko.yourburger.repos;

import com.danylko.yourburger.entities.ProductOrder;
import org.springframework.data.repository.CrudRepository;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {

    ProductOrder findByName(String name);
}
