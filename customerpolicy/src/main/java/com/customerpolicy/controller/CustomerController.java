package com.customerpolicy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customerpolicy.bean.LoginRequest;
import com.customerpolicy.entity.Customer;
import com.customerpolicy.exception.CustomerNotFoundException;
import com.customerpolicy.service.CustomerService;

import ch.qos.logback.core.net.LoginAuthenticator;

@RestController
public class CustomerController {
@Autowired
CustomerService customerService;

@PostMapping("/register/customer")
public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
    Customer registeredCustomer = customerService.registerCustomer(customer);
    return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
}
@PostMapping("/customer/login")
public ResponseEntity<Customer> login(@RequestBody LoginRequest loginRequest) throws CustomerNotFoundException {
    Customer customer = customerService.login(loginRequest.getEmail(), loginRequest.getPassword());
    return new ResponseEntity<>(customer, HttpStatus.OK);
}

}

