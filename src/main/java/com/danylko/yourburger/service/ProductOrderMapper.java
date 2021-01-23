package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.ProductOrder;

import java.util.Set;

public interface ProductOrderMapper {

    Set<ProductOrder> getProductOrderList(String jsonStr);
}
