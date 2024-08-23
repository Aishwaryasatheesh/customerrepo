package com.customer.customerpolicy.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchedId;
    private Long policyId;
    private Double premium;
    private LocalDate MaturityDate;
    private LocalDate purchaseDate;
    private String statu;

    @ManyToOne
    private Customer customer;
}
