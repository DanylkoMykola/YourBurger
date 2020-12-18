package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.service.ProductService;
import com.danylko.yourburger.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;


@Controller
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final StorageService storageService;

    public ProductController(ProductService productService, StorageService storageService) {
        this.productService = productService;
        this.storageService = storageService;
    }

    @GetMapping({"/", "/products"})
    public String list(Model uiModel) {
        logger.info("Start method list");
        List<Product> products = productService.findAll();
        storageService.loadAll(products);
        uiModel.addAttribute("products", products);
        logger.info("End method list");
        return "index";
    }

    @PostMapping("/createproduct")
    public String createProd( @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String price,
                              @RequestParam(name = "image") MultipartFile file,
                              RedirectAttributes attr
                                   ) {
        logger.info("Start method saveProd");
        String fileName = storageService.store(file);
        Product product = new Product(name, fileName, description, Integer.parseInt(price));
        logger.info("File Saved");
        product.setImage(fileName);
        productService.save(product);
        return "adminCreateProduct";
    }

    @GetMapping("/createproduct")
    public String createProd(Model model) {
        return "adminCreateProduct";
    }
    @DeleteMapping("/delete")
    public String delete(@RequestParam String name) {
       Product product = productService.findByName(name);
       storageService.delete(product.getImage());
       productService.delete(product);
       return "delete";
    }
}
