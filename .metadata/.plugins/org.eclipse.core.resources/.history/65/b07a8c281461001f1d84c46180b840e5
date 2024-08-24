package com.customer.customerpolicy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.customerpolicy.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);

}
