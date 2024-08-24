package com.policy.policymodule.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String policyName;
    private String coverageType; 
    private String description;
    private double premium;
    private Integer  duration;  
    private boolean active;
	public Integer totalCustomers() {
		// TODO Auto-generated method stub
		return 0;
	}
}

