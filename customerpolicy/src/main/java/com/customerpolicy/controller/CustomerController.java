package com.customerpolicy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customerpolicy.bean.LoginRequest;
import com.customerpolicy.entity.Customer;
import com.customerpolicy.exception.CustomerNotFoundException;
import com.customerpolicy.service.CustomerService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
@Autowired
CustomerService customerService;
Logger logger = LoggerFactory.getLogger(CustomerController.class);

@PostMapping
public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) {
	logger.info("============================");
	logger.info("addCustomer() called from controller layer");
    Customer registeredCustomer = customerService.registerCustomer(customer);
    logger.info("============================");
	logger.info("addCustomer() is ended from controller layer");
	logger.info("customer={}",customer);
    return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
}
@PostMapping("/login")
public ResponseEntity<Customer> login(@Valid @RequestBody LoginRequest loginRequest) throws CustomerNotFoundException {
	logger.info("============================");
	logger.info("login() called from controller layer");
    Customer customer = customerService.login(loginRequest.getEmail(), loginRequest.getPassword());
    logger.info("============================");
    logger.info("customer = {}",customer);
    logger.info("login() ended from controller layer");
    return new ResponseEntity<>(customer, HttpStatus.OK);
}

}

