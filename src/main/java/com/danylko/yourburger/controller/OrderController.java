package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.*;
import com.danylko.yourburger.service.FacilityService;
import com.danylko.yourburger.service.OrderService;
import com.danylko.yourburger.service.ProductOrderMapper;
import com.danylko.yourburger.util.DateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FacilityService facilityService;

    @GetMapping("/order")
    public String getOrderPage(Model model) {
        List<Facility> facilities = facilityService.findAll();
        model.addAttribute("facilities", facilities);
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
        List<ProductOrder> productOrderList = productOrderMapper.getProductOrderList(orderList);
        logger.info(productOrderList.toString());
        logger.info(city);
        Facility facility = facilityService.findByServingCity(city);
        if (facility == null)
            logger.info("facility == null");
        else
            logger.info(facility.toString());
        Address address = new Address(city, street, streetNumber, apartment);
        Customer customer = new Customer(firstName, lastName, phoneNumber, email);

        DateFormatter dateFormatter = new DateFormatter();
        Date date = dateFormatter.getDate();
        logger.info(customer.toString());
        Order order = new Order(productOrderList, facility, customer, date, address, Integer.parseInt(sum));

        logger.info(address.toString());
        logger.info(order.toString());
        orderService.save(order);

        return "index";
    }
}
