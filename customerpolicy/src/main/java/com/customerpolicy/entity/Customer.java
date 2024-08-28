package com.customerpolicy.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.customerpolicy.bean.Policy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@NotNull(message = "Name is required")
@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
private String name;
@NotNull(message = "Email is required")
@Email(message = "Email should be valid")
private String email;
@NotNull(message = "Password is required")
@Size(min = 3, message = "Password should have at least 6 characters")
private String password;
@NotNull(message = "Address is required")
private String address;
@NotNull(message = "Phone number is required")
private Long phoneNumber;
@NotNull(message = "Registration date is required")
private LocalDate registrationDate;

}


