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

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @Override
    public String store(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + fileName);
            }
            if (fileName.contains("..")) {
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + fileName);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, this.rootLocation.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
            } catch (IOException e) {
            throw new StorageException("Failed to store file " + fileName, e);
        }
        return fileName;
    }

    @Override
    public String load(String fileName) {
        return this.rootLocation.resolve(fileName).toString();
    }

    @Override
    public List<String> loadAll(List<Product> products) {
        for(Product product : products) {
            String fileName =  product.getImage();
            logger.info("File name: " + fileName);
            String fullPath;
            if (fileName != null) {
                fullPath = load(fileName);
            }
             else {
                 fullPath = load("default.jpg");
             }
            logger.info("Full path to file: " + fullPath);
            fullPath = fullPath.replace( "\\", "/");
            product.setImage(fullPath);
        }
        return null;
    }

    @Override
    public void delete(String fileName) {
        try {
            Files.delete(this.rootLocation.resolve(fileName));
        } catch (IOException e) {
            throw new StorageException("Failed to delete file " + fileName, e);
        }

    }
}
