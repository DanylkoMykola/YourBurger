package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.service.ProductService;
import com.danylko.yourburger.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


@Controller
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private StorageService storageService;

   @GetMapping({"/", "/products"})
    public String list(Model uiModel) {
        logger.info("Start method list");
        List<Product> products = productService.findAll();
        storageService.loadAll(products);
        uiModel.addAttribute("products", products);
        logger.info("End method list");
        return "index";
    }

    @PostMapping("/productform")
    public String createProd( @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String price,
                              @RequestParam(name = "image") MultipartFile file
                                   ) {
        logger.info("Start method saveProd");
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(Integer.parseInt(price));
        logger.info(product.toString());
        String fileName = storageService.store(file);
        logger.info("File Saved");
        product.setImage(fileName);
        productService.save(product);
        return "productform";
    }

    @GetMapping("/productform")
    public String createProd(Model model) {
        model.addAttribute("product", new Product());
        return "productform";
    }
    @DeleteMapping("/delete")
    public String delete(@RequestParam String name) {
       Product product = productService.findByName(name);
       storageService.delete(product.getImage());
       productService.delete(product);
       return "delete";
    }
}
