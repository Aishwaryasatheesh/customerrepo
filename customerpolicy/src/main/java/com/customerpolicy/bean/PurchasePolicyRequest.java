package com.customerpolicy.bean;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchasePolicyRequest {
	 private Long customerId;
	    private Long policyId;
	    private double premium;
	    private LocalDateTime purchaseDate;
	    private LocalDateTime maturityDate;

}
