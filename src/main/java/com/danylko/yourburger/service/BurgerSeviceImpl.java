package com.danylko.yourburger.service;

import com.danylko.yourburger.repos.BurgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BurgerSeviceImpl implements BurgerService {

    BurgerRepository burgerRepository;

    @Autowired
    public

}
