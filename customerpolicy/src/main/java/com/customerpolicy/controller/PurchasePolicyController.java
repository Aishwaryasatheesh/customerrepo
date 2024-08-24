package com.customerpolicy.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customerpolicy.bean.PurchasePolicyRequest;
import com.customerpolicy.entity.PurchasedPolicy;
import com.customerpolicy.exception.CustomerNotFoundException;
import com.customerpolicy.exception.PolicyNotFoundException;
import com.customerpolicy.service.PurchasePolicyService;

@RestController
@RequestMapping("/purchase")
public class PurchasePolicyController {
	@Autowired
	PurchasePolicyService purchasePolicyService;
	@PostMapping("/{customerId}/{policyId}")
	public ResponseEntity<?> purchasePolicy(@PathVariable Long customerId, @PathVariable Long policyId, @RequestBody PurchasePolicyRequest request) {
	    try {
	        PurchasedPolicy purchasedPolicy = purchasePolicyService.purchasePolicy(customerId, policyId, request);
	        return new ResponseEntity<>(purchasedPolicy, HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}
}