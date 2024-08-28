package com.customerpolicy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
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
    @JoinColumn(name = "customer_Id")
    @JsonIgnore
    @NotNull(message = "customerId should not be null")
    		private Customer customer;
    @NotNull(message = "Policy ID is required")
    private Long policyId;
    @NotNull(message = "Premium is required")
    private Double premium;
    @NotNull(message = "Maturitydate is required")
    private LocalDateTime maturityDate;
    @NotNull(message = "purchaseDate is required")
    private LocalDateTime purchaseDate;
    private String status;
    
}
