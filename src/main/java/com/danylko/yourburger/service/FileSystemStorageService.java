package com.danylko.yourburger.service;

import com.danylko.yourburger.config.StorageProperties;
import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.exceptions.StorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class FileSystemStorageService implements StorageService {

    Logger logger = LoggerFactory.getLogger(FileSystemStorageService.class);

    private final Path resourceLocation;
    private final Path uploadFileLocation;
    private final String defaultImg;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.resourceLocation = Paths.get(properties.getResourceLocation());
        this.uploadFileLocation = Paths.get(properties.getUploadFileLocation());
        this.defaultImg = properties.getDefaultImg();
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(uploadFileLocation.toAbsolutePath());
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location ", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                return defaultImg;
            }
            if (fileName.contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + fileName);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.uploadFileLocation.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
            } catch (IOException e) {
            throw new StorageException("Failed to store file " + fileName, e);
        }
        return fileName;
    }

    @Override
    public String load(String fileName) {
        return this.resourceLocation.resolve(fileName).toString();
    }

    @Override
    public void loadAll(List<Product> products) {
        for(Product product : products) {
            String fileName =  product.getImage();
            String fullPath;
            if (fileName != null) {
                fullPath = load(fileName);
            }
             else {
                 fullPath = load(defaultImg);
             }
            product.setImage(fullPath);
        }
    }

    @Override
    public void delete(String fileName) {
        try {
            Files.delete(this.resourceLocation.resolve(fileName));
        } catch (IOException e) {
            throw new StorageException("Failed to delete file " + fileName, e);
        }

    }
}
