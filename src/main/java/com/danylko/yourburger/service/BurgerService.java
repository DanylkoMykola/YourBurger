package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Burger;

import java.util.List;

public interface BurgerService {

    List<Burger> findAll();
    Burger findById(Long id);
    Burger save(Burger burger);
    void delete(Burger burger);
}
