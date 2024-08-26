package com.policy.policymodule.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;
    @NotBlank(message = "Policy name is required")
    @Size(min = 3, max = 50, message = "Policy name must be between 3 and 50 characters")
    private String policyName;
    @NotBlank(message = "Coverage type is required")
    private String coverageType;
    private String description;
    @NotNull(message = "Premium is required")
    private double premium;
    @NotNull(message = "Duration is required")
    private Integer  duration; 
    @NotNull(message = "Active status is required")
    private boolean active;
	public Integer totalCustomers() {
		// TODO Auto-generated method stub
		return 0;
	}
}

