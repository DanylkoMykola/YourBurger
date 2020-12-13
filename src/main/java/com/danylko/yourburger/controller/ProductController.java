package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.FileResponse;
import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.service.ProductService;
import com.danylko.yourburger.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Controller
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private StorageService storageService;

   @GetMapping("/products")
    public String list(Model uiModel) {
        logger.info("Start method list");
        List<Product> products = productService.findAll();
        uiModel.addAttribute("products", products);
        logger.info("End method list");
        return "product";
    }

    @PostMapping("/productform")
    public FileResponse createProd( @ModelAttribute Product product,
                                   @RequestParam("image") MultipartFile file,
                                   Model model) {
        logger.info("Strart method saveProd");
        model.addAttribute("product", product);
        String name = storageService.store(file);
        String uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(name)
                .toUriString();
        logger.info("File Saved");
        product.setImage(name);
        productService.save(product);
        return new FileResponse(name, uri, file.getContentType(), file.getSize());
    }
    @GetMapping("/productform")
    public String createProd(Model model) {
        model.addAttribute("product", new Product());
        return "productform";
    }
}
