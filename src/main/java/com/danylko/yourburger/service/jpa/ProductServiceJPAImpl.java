package com.danylko.yourburger.service.jpa;

import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.repos.ProductRepository;
import com.danylko.yourburger.service.ProductService;
import com.danylko.yourburger.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@Qualifier("productServiceJPA")
public class ProductServiceJPAImpl implements ProductService {

    private ProductRepository productRepository;
    private StorageService storageService;

    public ProductServiceJPAImpl(ProductRepository productRepository,
                                 StorageService storageService) {
        this.productRepository = productRepository;
        this.storageService = storageService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Iterable<Product> iterable = productRepository.findAll();
        iterable.forEach(products::add);
        return products;
    }

    @Override
    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product findByName(String name) { return productRepository.findByName(name); }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Product product) { productRepository.delete(product); }

    @Override
    public void checkEmptyFields(Product product,
                                 String name,
                                 String description,
                                 String price,
                                 MultipartFile image) {
        if (!name.isEmpty()) {
            product.setName(name);
        }
        if (!description.isEmpty()) {
            product.setDescription(description);
        }
        if (!price.isEmpty()) {
            product.setPrice(Integer.parseInt(price));
        }
        if (!image.isEmpty()) {
            String fileName = storageService.store(image);
            product.setImage(fileName);
        }
    }

}
