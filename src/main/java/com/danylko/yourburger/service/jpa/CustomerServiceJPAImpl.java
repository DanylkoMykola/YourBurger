package com.danylko.yourburger.service.jpa;

import com.danylko.yourburger.entities.Customer;
import com.danylko.yourburger.repos.CustomerRepository;
import com.danylko.yourburger.service.CustomerService;
import com.danylko.yourburger.util.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class CustomerServiceJPAImpl implements CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerServiceJPAImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Set<Customer> findAll() {
        Set<Customer> customers = new HashSet<>();
        Iterable<Customer> iterable = customerRepository.findAll();
        iterable.forEach(customers::add);
        return customers;
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public Customer checkIfNewCustomer(Customer customer) {
        Customer customerFromDB = findByPhoneNumber(customer.getPhoneNumber());
        if (customerFromDB != null) {
            return customerFromDB;
        }
        customer.setPassword(PasswordGenerator.ganeratePassword());
        return customer;
    }
}
