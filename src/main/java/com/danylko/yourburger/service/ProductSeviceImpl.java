package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ProductSeviceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        List<Product> burgers = new ArrayList<>();
        Iterable<Product> iterable = productRepository.findAll();
        iterable.forEach(burgers::add);
        return burgers;
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product save(Product burger) {
        return productRepository.save(burger);
    }

    @Override
    public void delete(Product burger) {
        productRepository.delete(burger);
    }

}
