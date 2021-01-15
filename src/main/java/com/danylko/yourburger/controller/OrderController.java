package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.Address;
import com.danylko.yourburger.entities.Customer;
import com.danylko.yourburger.entities.Order;
import com.danylko.yourburger.repos.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    @GetMapping("/order")
    public String getOrderPage() {
        return "order";
    }

    @PostMapping("/order")
    public String makeOrder(Model model) {
        Customer customer = new Customer();
        Address address = new Address();
        Order order = new Order();
        model.addAttribute("customer", customer);
        model.addAttribute("address", address);
        return "order";
    }
}
