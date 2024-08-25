package com.customerpolicy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customerpolicy.entity.Customer;
import com.customerpolicy.exception.CustomerNotFoundException;
import com.customerpolicy.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
	}
	public Customer login(String email, String password) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByEmailAndPassword(email, password).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException("Invalid name or password");
        return customer;
    }
	}
