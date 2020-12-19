package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);
    Product findByName(String name);
    Product save(Product product);
    void delete(Product product);

}
