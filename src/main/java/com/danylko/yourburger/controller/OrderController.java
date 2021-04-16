package com.danylko.yourburger.controller;

import com.danylko.yourburger.config.EmailProperties;
import com.danylko.yourburger.entities.*;
import com.danylko.yourburger.mail.EmailService;
import com.danylko.yourburger.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    private ProductOrderMapper productOrderMapper;
    private OrderService orderService;
    private FacilityService facilityService;
    private CustomerService customerService;
    private EmailService emailService;
    private EmailProperties emailProperties;
    private AddressService addressService;

    public OrderController(ProductOrderMapper productOrderMapper,
                            OrderService orderService,
                            FacilityService facilityService,
                            CustomerService customerService,
                            EmailService emailService,
                            EmailProperties emailProperties,
                            AddressService addressService) {
        this.productOrderMapper = productOrderMapper;
        this.orderService = orderService;
        this.facilityService = facilityService;
        this.customerService = customerService;
        this.emailService = emailService;
        this.emailProperties = emailProperties;
        this.addressService = addressService;
    }

    @GetMapping("/order")
    public String getOrderPage(Principal principal, Model model) {
        List<Facility> facilities = facilityService.findAll();
        model.addAttribute("facilities", facilities);
        model.addAttribute("order", new Order());

        if (principal != null) {
            Customer customer = customerService.findByPhoneNumber(principal.getName());
            Address address = addressService.findByCustomerId(customer.getCustId());
            model.addAttribute("customer", customer);
            model.addAttribute("address", address);
        }
        return "order";
    }

    @PostMapping("/order")
    public String makeOrder(@ModelAttribute Order order,
                            @ModelAttribute Address authAddress,
                            @ModelAttribute Customer authCustomer) {
        Customer customer;
        logger.info(authCustomer.toString() + "111111111111111111" + authAddress.toString());
        if (authCustomer.getPhoneNumber() != null && authAddress.getCity() != null) {
            customer = customerService.getExistCustomerOrGeneratePasswordForNewCustomer(authCustomer);
            order.setAddress(authAddress);
        } else {
            customer = customerService.getExistCustomerOrGeneratePasswordForNewCustomer(order.getCustomer());
        }

        List<ProductOrder> productOrderList = productOrderMapper.getProductOrderList(order.getJsonOrderlist());
        Facility facility = facilityService.findByServingCity(order.getAddress().getCity());

        order.setProductOrderList(productOrderList);
        order.setCustomer(customer);
        order.setFacility(facility);

        order.setOrderDate(new Date());
        orderService.save(order);

        try {
            emailService.sendMessageUsingThymeleafTemplate(emailProperties.getFacilityEmail(),
                    emailProperties.getHtmlTemplateOrderResult(),
                    Map.of("order", order));
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("Email message not sent: " + e);
            return "inform/error";
        }
        logger.info(productOrderList.toString());
        return "inform/success";
    }

}
