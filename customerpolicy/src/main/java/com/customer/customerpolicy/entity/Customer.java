package com.customer.customerpolicy.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long customerId;
private String name;
private String email;
private String password;
private String address;
private Long phoneNumber;
private LocalDate registrationDate;
}


