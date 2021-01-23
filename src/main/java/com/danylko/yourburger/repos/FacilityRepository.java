package com.danylko.yourburger.repos;

import com.danylko.yourburger.entities.Facility;
import org.springframework.data.repository.CrudRepository;

public interface FacilityRepository extends CrudRepository<Facility, Long> {

    Facility findByServingCity(String servinCity);
}
