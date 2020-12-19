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

    @GetMapping("/admin-create")
    public String productForm() {
        return "admin-create";
    }

    @GetMapping("/admin-update")
    public String update() {return "admin-update";}

    @PostMapping("/admin-create")
    public String productForm(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String price,
                              @RequestParam(name = "image") MultipartFile file,
                              RedirectAttributes attributes
                                   ) {
        logger.info("Start method saveProd");
        String fileName = storageService.store(file);
        Product product = new Product(name, fileName, description, Integer.parseInt(price));
        logger.info("File Saved");
        productService.save(product);
        attributes.addFlashAttribute("message", "Продукт успішно збережено!");
        return "redirect:/admin-create";
    }
    //TODO
    @PostMapping("/admin-update")
    public String update(@RequestParam(name = "prodName") String prodName,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "description") String description,
                         @RequestParam(name = "price") String price,
                         @RequestParam(name = "image") MultipartFile file,
                         RedirectAttributes attributes) {
        Product product = productService.findByName(prodName);
        if (product == null) {
            attributes.addFlashAttribute("message", "Продукт з такою назвою не існує!");
            return "redirect:/admin-update";
        }
        if (!name.isEmpty()) {
            product.setName(name);
        } else if (!description.isEmpty()) {
            product.setDescription(description);
        } else if (!price.isEmpty()) {
            product.setPrice(Integer.parseInt(price));
        } else if (file.isEmpty()) {
            String fileName = storageService.store(file);
            product.setImage(fileName);
        }
        productService.save(product);
        attributes.addFlashAttribute("message", "Дані були продукту змінені!");
        return "redirect:/admin-update";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam String name) {
       Product product = productService.findByName(name);
       storageService.delete(product.getImage());
       productService.delete(product);
       return "delete";
    }
}
