package com.danylko.yourburger.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init();
    String store(MultipartFile multipartFile);
    void delete();
}
