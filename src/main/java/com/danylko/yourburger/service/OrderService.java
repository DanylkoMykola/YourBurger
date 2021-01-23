package com.danylko.yourburger.service;


import com.danylko.yourburger.entities.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order findById(Long id);
    void save(Order order);
    void delete(Order order);
}
