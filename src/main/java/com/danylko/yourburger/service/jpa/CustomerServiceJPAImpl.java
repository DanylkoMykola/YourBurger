package com.danylko.yourburger.service.jpa;

import com.danylko.yourburger.config.EmailProperties;
import com.danylko.yourburger.entities.Customer;
import com.danylko.yourburger.mail.EmailService;
import com.danylko.yourburger.repos.CustomerRepository;
import com.danylko.yourburger.service.CustomerService;
import com.danylko.yourburger.util.PasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;

@Transactional
@Service
public class CustomerServiceJPAImpl implements CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerServiceJPAImpl.class);

    private final CustomerRepository customerRepository;
    private final EmailService emailService;
    private final EmailProperties emailProperties;
   // private final PasswordEncoder passwordEncoder;

    public CustomerServiceJPAImpl(CustomerRepository customerRepository,
                                  EmailService emailService,
                                  EmailProperties emailProperties
                                 ) {
        this.customerRepository = customerRepository;
        this.emailService = emailService;
        this.emailProperties = emailProperties;
       // this.passwordEncoder = passwordEncoder;
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
        String password = PasswordGenerator.ganeratePassword();
        customer.setPassword(password);
        sendEmailWithPassword(customer);
        customer.setPassword(new BCryptPasswordEncoder(12).encode(password));
        return customer;
    }
    private void sendEmailWithPassword(Customer customer) {
        try {
            Map<String, Object> modelAttributes = new HashMap<>();
            modelAttributes.put("customer", customer);
            emailService.sendMessageUsingThymeleafTemplate(
                    customer.getEmail(),
                    emailProperties.getHtmlTemplateCustomer(),
                    modelAttributes);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
