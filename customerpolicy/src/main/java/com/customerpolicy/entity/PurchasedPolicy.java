package com.customerpolicy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchasedPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchasedId;
    
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    
    private Long policyId;
    private Double premium;
    private LocalDateTime MaturityDate;
    private LocalDateTime purchaseDate;
    private String status;
    
}
