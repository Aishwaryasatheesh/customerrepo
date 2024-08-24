package com.customerpolicy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Policy {

	    private Long policyId;
	    private String policyName;
	    private String coverageType;
	    
	    private Integer  duration; 
	    private boolean active;
}
