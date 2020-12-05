package com.danylko.yourburger.repos;

import com.danylko.yourburger.entities.Burger;
import org.springframework.data.repository.CrudRepository;

public interface BurgerRepository extends CrudRepository<Burger, Long> {
}
