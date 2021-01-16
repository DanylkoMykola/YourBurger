package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Product;
import com.danylko.yourburger.entities.ProductOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ProductOrderMapper {


    public Map<String, String> getProductOrderList(String jsonStr)  {
        Map<String, String> stringMap = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            stringMap = objectMapper.readValue(jsonStr, new TypeReference<Map<String, String>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringMap;
    }
}
