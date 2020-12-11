package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Controller
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Value("${upload.path}")
    private String uploadPath;

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
    public String saveProd(@RequestParam("image") MultipartFile file,
                             @ModelAttribute Product product) {
        try {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String fileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + fileName));
                product.setImage(fileName);
            }
        } catch (IOException  e) {
            logger.info("IOException. 'saveProd()' method have a problem to upload/download a file!");
        }
        productService.save(product);
        return "productform";
    }
    @GetMapping("/productform")
    public String createBurger(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", product);
        return "productform";
    }
}
