package com.danylko.yourburger.repos;

import com.danylko.yourburger.entities.Burger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BurgerRepository extends CrudRepository<Burger, Long> {
}
