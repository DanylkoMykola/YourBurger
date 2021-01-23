package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Product;

import java.util.Set;

public interface ProductOrderMapper {

    Set<Product> getProductOrderList(String jsonStr);
}
