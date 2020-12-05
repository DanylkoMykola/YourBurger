package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Burger;
import com.danylko.yourburger.repos.BurgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
@Service
public class BurgerSeviceImpl implements BurgerService {

    private BurgerRepository burgerRepository;

    @Autowired
    public void setBurgerRepository(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Burger> findAll() {
        List<Burger> burgers = new ArrayList<>();
        Iterable<Burger> iterable = burgerRepository.findAll();
        iterable.forEach(burgers::add);
        return burgers;
    }

    @Override
    @Transactional(readOnly = true)
    public Burger findById(Long id) {
        return burgerRepository.findById(id).get();
    }

    @Override
    public Burger save(Burger burger) {
        return burgerRepository.save(burger);
    }

    @Override
    public void delete(Burger burger) {
        burgerRepository.delete(burger);
    }

}
