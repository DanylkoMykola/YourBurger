package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);
    Product save(Product burger);
    void delete(Product burger);
}
