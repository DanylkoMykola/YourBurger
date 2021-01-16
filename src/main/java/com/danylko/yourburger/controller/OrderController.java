package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.Address;
import com.danylko.yourburger.entities.Customer;
import com.danylko.yourburger.entities.Order;
import com.danylko.yourburger.entities.ProductOrder;
import com.danylko.yourburger.repos.CustomerRepository;
import com.danylko.yourburger.service.ProductOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    ProductOrderMapper productOrderMapper;

    @GetMapping("/order")
    public String getOrderPage() {
        return "order";
    }

    @PostMapping("/order")
    public String makeOrder(@RequestParam String orderList,
                            @RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String phoneNumber,
                            @RequestParam String email,
                            @RequestParam String city,
                            @RequestParam String street,
                            @RequestParam String streetNumber,
                            @RequestParam String apartment) {

        logger.info(orderList);
        //List<ProductOrder> productOrderList = productOrderMapper.getProductOrderList(orderList);
       // logger.info(productOrderList.toString());
      /*  model.addAttribute("customer", customer);
        model.addAttribute("address", address);

        logger.info(customer.toString());
        logger.info(address.toString());
        */
        return "index";
    }
}
