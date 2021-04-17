package com.danylko.yourburger.controller;

import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.service.ProductService;
import com.danylko.yourburger.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminProductController {

    private Logger logger = LoggerFactory.getLogger(AdminProductController.class);

    private ProductService productService;
    private StorageService storageService;

    public AdminProductController(@Qualifier("productServiceJPAImpl") ProductService productService,
                                  StorageService storageService) {
        this.productService = productService;
        this.storageService = storageService;
    }
    @GetMapping("/admin-create")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public String createProduct() {
        return "admin/admin-create";
    }

    @GetMapping("/admin-update")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public String update() {return "admin/admin-update";}

    @GetMapping("/admin-delete")
    @PreAuthorize("hasAnyAuthority('admin:read')")
    public String delete() {return "admin/admin-delete";}

    @PostMapping("/admin-create")
    @PreAuthorize("hasAnyAuthority('admin:write')")
    public String createProduct(@RequestParam String name,
                                @RequestParam String description,
                                @RequestParam String price,
                                @RequestParam(name = "image") MultipartFile file,
                                RedirectAttributes attributes
    ) {
        String fileName = storageService.store(file);
        Product product = new Product(name, fileName, description, Integer.parseInt(price));
        productService.save(product);
        attributes.addFlashAttribute("message", "Продукт успішно збережено!");
        return "redirect:/admin-create";
    }

    @PostMapping("/admin-update")
    @PreAuthorize("hasAnyAuthority('admin:write')")
    public String update(@RequestParam(name = "prodName") String prodName,
                         @RequestParam(name = "name") String name,
                         @RequestParam(name = "description") String description,
                         @RequestParam(name = "price") String price,
                         @RequestParam(name = "image") MultipartFile image,
                         RedirectAttributes attributes) {
        Product product = productService.findByName(prodName);
        if (product == null) {
            attributes.addFlashAttribute("message", "Продукта з такою назвою не існує!");
            return "redirect:/admin-update";
        }
        productService.checkEmptyFields(product, name, description, price, image);
        productService.save(product);
        attributes.addFlashAttribute("message", "Дані продукту були змінені!");
        return "redirect:/admin-update";
    }

    @PostMapping("admin-delete")
    @PreAuthorize("hasAnyAuthority('admin:write')")
    public String delete(@RequestParam String name, RedirectAttributes attributes) {
        Product product = productService.findByName(name);
        if (product == null) {
            attributes.addFlashAttribute("message", "Продукта з такою назвою не існує!");
            return "redirect:/admin-delete";
        }
        // storageService.delete(product.getImage());
        productService.delete(product);
        attributes.addFlashAttribute("message", "Продукт стерто!!");
        return "redirect:/admin-delete";
    }
}
