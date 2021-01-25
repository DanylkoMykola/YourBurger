package com.danylko.yourburger.repos;

import com.danylko.yourburger.entities.Facility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface FacilityRepository extends CrudRepository<Facility, Long> {

    @Query("select * from facilities where serving_city=:servingCity")
    Facility findByServingCity(@Param("servingCity") String servingCity);
}
