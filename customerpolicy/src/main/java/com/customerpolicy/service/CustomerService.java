package com.customerpolicy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerpolicy.entity.Customer;
import com.customerpolicy.exception.CustomerNotFoundException;
import com.customerpolicy.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	
	public Customer registerCustomer(Customer customer) {
		logger.info("============================");
		logger.info("addCustomer()  called from service layer");
		
		logger.info("addCustomer()  ended from service layer");
		logger.info("============================");
        return customerRepository.save(customer);
	}
	public Customer login(String email, String password) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByEmailAndPassword(email, password).orElse(null);
        if (customer == null) {
        	logger.info("============================");
        	logger.error("Customer email and password not found for email= {}",email);
        	logger.info("============================");
            throw new CustomerNotFoundException("Invalid name or password");
        }
        logger.info("findByEmailAndPassword is ended from service layer");
        return customer;
    
}
}