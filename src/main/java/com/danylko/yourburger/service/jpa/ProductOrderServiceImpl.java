package com.danylko.yourburger.service.jpa;

import com.danylko.yourburger.entities.ProductOrder;
import com.danylko.yourburger.repos.ProductOrderRepository;
import com.danylko.yourburger.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    private ProductOrderRepository productOrderRepository;

    @Autowired
    public void setProductOrderRepository(ProductOrderRepository productOrderRepository) {
        this.productOrderRepository = productOrderRepository;
    }

    @Override
    public Set<ProductOrder> findAll() {
        Set<ProductOrder> productOrders = new HashSet<>();
        Iterable<ProductOrder> iterable = productOrderRepository.findAll();
        iterable.forEach(productOrders::add);
        return productOrders;
    }

    @Override
    public ProductOrder findByName(String name) {
        return productOrderRepository.findByName(name);
    }

    @Override
    public void save(ProductOrder productOrder) {
        productOrderRepository.save(productOrder);
    }

    @Override
    public void delete(ProductOrder productOrder) {
        productOrderRepository.delete(productOrder);
    }
}
