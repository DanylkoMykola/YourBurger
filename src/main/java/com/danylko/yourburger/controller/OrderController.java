package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.*;
import com.danylko.yourburger.service.ProductOrderMapperImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    ProductOrderMapperImpl productOrderMapper;

    @GetMapping("/order")
    public String getOrderPage() {
        return "order";
    }

    @PostMapping("/order")
    public String makeOrder(@RequestParam String orderList,
                            @RequestParam String sum,
                            @RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String phoneNumber,
                            @RequestParam String email,
                            @RequestParam String city,
                            @RequestParam String street,
                            @RequestParam String streetNumber,
                            @RequestParam String apartment) {

       // logger.info(orderList);
        Set<ProductOrder> productOrderList = productOrderMapper.getProductOrderList(orderList);
        //logger.info(productOrderList.toString());
        Address address = new Address(city, street, streetNumber, apartment);
        Customer customer = new Customer(firstName, lastName, phoneNumber, email);
        Order order = new Order(productOrderList, null, customer, address, Integer.parseInt(sum));
        logger.info(customer.toString());
        logger.info(address.toString());

        return "index";
    }
}