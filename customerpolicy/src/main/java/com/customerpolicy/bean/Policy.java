package com.customerpolicy.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Policy {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long policyId;
	    private String policyName;
	    private String coverageType;
	    private Integer  duration; 
	    private boolean active;
}
