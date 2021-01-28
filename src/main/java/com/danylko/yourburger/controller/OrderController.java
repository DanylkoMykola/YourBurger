package com.danylko.yourburger.controller;

import com.danylko.yourburger.config.EmailProperties;
import com.danylko.yourburger.config.StorageProperties;
import com.danylko.yourburger.entities.*;
import com.danylko.yourburger.mail.EmailService;
import com.danylko.yourburger.service.CustomerService;
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

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

@Controller
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FacilityService facilityService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailProperties emailProperties;


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

        Address address = new Address(city, street, streetNumber, apartment);
        Customer customer = new Customer(firstName, lastName, phoneNumber, email);

        Customer customerChecked = customerService.checkIfNewCustomer(customer);

        DateFormatter dateFormatter = new DateFormatter();
        Date date = dateFormatter.getDate();
        logger.info(customer.toString());
        Order order = new Order(productOrderList, facility, customerChecked, date, address, Integer.parseInt(sum));

        Map<String, Object> modelAtt = new HashMap<>();
        modelAtt.put("order", order);

        try {

            emailService.sendMessageUsingThymeleafTemplate(emailProperties.getFacilityEmail(),
                    emailProperties.getHtmlTemplateOrderResult(), modelAtt);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //emailService.sendSimpleMessage(email, order.toString());
        logger.info(address.toString());
        logger.info(order.toString());
        orderService.save(order);

        return "index";
    }

    //test
   /* @GetMapping("/email-order-result")
    public String tempalteOrder(Model model, Order order){
        model.addAttribute("order", order);
        return "email-order-result";
    }*/
}
