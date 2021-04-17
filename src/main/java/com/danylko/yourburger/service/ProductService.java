package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    List<Product> findAll();
    Product findById(Long id);
    Product findByName(String name);
    void save(Product product);
    void delete(Product product);
    void checkEmptyFields(Product product,
                          String name,
                          String description,
                          String price,
                          MultipartFile image);

}
