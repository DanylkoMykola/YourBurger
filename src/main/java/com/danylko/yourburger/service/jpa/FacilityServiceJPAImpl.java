package com.danylko.yourburger.service.jpa;

import com.danylko.yourburger.entities.Facility;
import com.danylko.yourburger.repos.FacilityRepository;
import com.danylko.yourburger.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Transactional
@Service
public class FacilityServiceJPAImpl implements FacilityService {

    private FacilityRepository facilityRepository;

    @Autowired
    public void setFacilityRepository(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public List<Facility> findAll() {
        List<Facility> facilities = new ArrayList<>();
        Iterable<Facility> iterable = facilityRepository.findAll();
        iterable.forEach(facilities::add);
        return facilities;
    }

    @Override
    public Facility findByServingCity(String servingCity) {
        return facilityRepository.findByServingCity(servingCity);
    }

    @Override
    public void save(Facility facility) {
        facilityRepository.save(facility);
    }

    @Override
    public void delete(Facility facility) {
        facilityRepository.delete(facility);
    }
}
