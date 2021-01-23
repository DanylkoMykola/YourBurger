package com.danylko.yourburger.service;

import com.danylko.yourburger.entities.Facility;

import java.util.Set;

public interface FacilityService {

    Set<Facility> findAll();
    Facility findByServingCity(String servingCity);
    void save(Facility facility);
    void delete(Facility facility);
}
