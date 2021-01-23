package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.util.Set;


@Component
public class ProductOrderMapperImpl implements ProductOrderMapper {


    public Set<Product> getProductOrderList(String jsonStr)  {
        Set<Product> productOrderList = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType javaTypes = objectMapper.getTypeFactory()
                    .constructCollectionType(Set.class, Product.class);
            productOrderList = objectMapper.readValue(jsonStr, javaTypes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productOrderList;
    }
}
