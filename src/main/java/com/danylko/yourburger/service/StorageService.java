package com.danylko.yourburger.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init();
    String store(MultipartFile multipartFile);
    String load(String fileName);
    void delete(String fileName);
}
