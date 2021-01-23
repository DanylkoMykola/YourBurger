package com.danylko.yourburger.repos;

import com.danylko.yourburger.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
