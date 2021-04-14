package com.danylko.yourburger.service.jpa;

import com.danylko.yourburger.entities.Address;
import com.danylko.yourburger.repos.AddressRepository;
import com.danylko.yourburger.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class AddressServiceJPAImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Set<Address> findAll() {
        Set<Address> addresses = new HashSet<>();
        Iterable<Address> iterable = addressRepository.findAll();
        iterable.forEach(addresses::add);
        return null;
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address findByCity(String city) {
        return addressRepository.findByCity(city);
    }

    @Override
    public Address findByStreet(String street) {
        return addressRepository.findByStreet(street);
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }

    @Override
    public Address findByCustomerId(Long id) {
        return addressRepository.findAddressByCustomerId(id);
    }
}
