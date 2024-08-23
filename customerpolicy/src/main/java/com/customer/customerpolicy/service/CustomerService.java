package com.customer.customerpolicy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.customerpolicy.entity.Customer;
import com.customer.customerpolicy.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
        
	}
}