package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {

    void init();
    String store(MultipartFile multipartFile);
    String load(String fileName);
    List<String> loadAll(List<Product> products);
    void delete(String fileName);
}
