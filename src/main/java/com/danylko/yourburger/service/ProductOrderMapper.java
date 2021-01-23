package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.ProductOrder;

import java.util.List;

public interface ProductOrderMapper {

    List<ProductOrder> getProductOrderList(String jsonStr);
}
