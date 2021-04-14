package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.Address;
import com.danylko.yourburger.entities.Customer;
import com.danylko.yourburger.service.AddressService;
import com.danylko.yourburger.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class CustomerController {

    private Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    private final AddressService addressService;

    public CustomerController(CustomerService customerService,
                              AddressService addressService) {
        this.customerService = customerService;
        this.addressService = addressService;
    }

    @GetMapping("/auth/customer")
    public String getCustomer(Principal principal, Model model) {
       // log.info(principal.getName());
        if (principal != null) {
            Customer customer = customerService.findByPhoneNumber(principal.getName());
            Address address = addressService.findByCustomerId(customer.getCustId());
            model.addAttribute("customer", customer);
            model.addAttribute("address", address);
            return "auth/customer";
        }
        return "auth/login";
    }
}
