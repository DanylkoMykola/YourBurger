package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

   @GetMapping("/products")
    public String list(Model uiModel) {
        logger.info("Start method list");
        List<Product> products = productService.findAll();
        uiModel.addAttribute("products", products);
        logger.info("End method list");
        return "product";
    }

   /* @GetMapping(value = "/{id}")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        Burger burger = burgerService.findById(id);
        uiModel.addAttribute("burger", burger);
        return "show";
    }

    @GetMapping(name = "/edit/{id}")
    public String updateForm(@PathVariable Long id, Model uiModel) {
        uiModel.addAttribute("burger", burgerService.findById(id));
        return "update";
    }

    @GetMapping(name = "/new")
    public String createForm(Model uiModel) {
        Burger burger = new Burger();
        uiModel.addAttribute("burger", burger);
        return "update";
    }*/

    @PostMapping("/productform")
    public String saveBurger(@ModelAttribute Product product, Model model) {
        productService.save(product);
        return "productform";
    }
    @GetMapping("/productform")
    public String createBurger(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", product);
        return "productform";
    }
}
