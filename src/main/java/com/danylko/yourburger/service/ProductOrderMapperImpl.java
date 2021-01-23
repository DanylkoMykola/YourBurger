package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.entities.ProductOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Component;

import java.io.IOException;

import java.util.Set;


@Component
public class ProductOrderMapperImpl implements ProductOrderMapper {


    public Set<ProductOrder> getProductOrderList(String jsonStr)  {
        Set<ProductOrder> productOrderList = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CollectionType javaTypes = objectMapper.getTypeFactory()
                    .constructCollectionType(Set.class, ProductOrder.class);
            productOrderList = objectMapper.readValue(jsonStr, javaTypes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productOrderList;
    }
}
