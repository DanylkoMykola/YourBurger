package com.danylko.yourburger.service.jpa;

import com.danylko.yourburger.entities.Order;
import com.danylko.yourburger.repos.OrderRepository;
import com.danylko.yourburger.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrderServiceJPAImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        Iterable<Order> iterable = orderRepository.findAll();
        iterable.forEach(orders::add);
        return orders;
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> optional = orderRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }


}
