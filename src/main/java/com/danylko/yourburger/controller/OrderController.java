package com.danylko.yourburger.controller;

import com.danylko.yourburger.config.EmailProperties;
import com.danylko.yourburger.config.StorageProperties;
import com.danylko.yourburger.entities.*;
import com.danylko.yourburger.mail.EmailService;
import com.danylko.yourburger.service.*;
import com.danylko.yourburger.util.DateFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
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

    @Autowired
    private AddressService addressService;


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
                            @ModelAttribute Address address,
                            Principal principal, Model model) {
        model.addAttribute("order", order);
        Customer customer;
        if (principal != null) {
            customer = customerService.findByPhoneNumber(principal.getName());
        } else {
            customer = customerService.checkIfNewCustomer(order.getCustomer());
        }

        List<ProductOrder> productOrderList = productOrderMapper.getProductOrderList(order.getJsonOrderlist());
        Facility facility = facilityService.findByServingCity(address.getCity());

        order.setAddress(address);
        logger.info(order.getAddress().toString());
        order.setProductOrderList(productOrderList);
        order.setCustomer(customer);
        order.setFacility(facility);

        order.setOrderDate(new Date());
        orderService.save(order);

        try {
            Map<String, Object> modelAtt = new HashMap<>();
            modelAtt.put("order", order);
            emailService.sendMessageUsingThymeleafTemplate(emailProperties.getFacilityEmail(),
                    emailProperties.getHtmlTemplateOrderResult(), modelAtt);
        } catch (MessagingException|IOException e) {
            e.printStackTrace();
            logger.error("Email message not sent: " + e);
            return "inform/error";
        }

        logger.info(productOrderList.toString());

        return "inform/success";
    }

}
