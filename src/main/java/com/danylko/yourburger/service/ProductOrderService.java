package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.ProductOrder;

import java.util.Set;

public interface ProductOrderService {

    Set<ProductOrder> findAll();
    ProductOrder findByName(String name);
    void save(ProductOrder productOrder);
    void delete(ProductOrder productOrder);
}
