package com.customerpolicy.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customerpolicy.bean.ApiResponse;
import com.customerpolicy.bean.PurchasePolicyRequest;
import com.customerpolicy.entity.PurchasedPolicy;
import com.customerpolicy.exception.CustomerNotFoundException;
import com.customerpolicy.service.PurchasePolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class PurchasePolicyController {
	@Autowired
	PurchasePolicyService purchasePolicyService;
	
	Logger logger = LoggerFactory.getLogger(PurchasePolicyController.class);

	@PostMapping("/policy")
    public ResponseEntity<ApiResponse> purchasePolicy(@Valid @RequestBody PurchasePolicyRequest request) {
        logger.info("===========================");
        logger.info("purchasePolicy() called from controller layer with request: {}", request);
        try {
            PurchasedPolicy purchasedPolicy = purchasePolicyService.purchasePolicy(request);
            logger.info("Successfully purchased policy: {}", purchasedPolicy);
            ApiResponse response = new ApiResponse("Successfully purchased the policy", HttpStatus.CREATED.value());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error occurred while purchasing policy: {}", e.getMessage());
            ApiResponse response = new ApiResponse("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } finally {
            logger.info("===========================");
        }
    }


	@GetMapping("/{customerId}")
    public ResponseEntity<List<PurchasedPolicy>> viewOwnPolicy(@PathVariable Long customerId) throws CustomerNotFoundException {
		logger.info("===========================");
		logger.info("viewOwnPolicy() called with customerId: {}", customerId);
        List<PurchasedPolicy> policies = purchasePolicyService.viewOwnPolicy(customerId);
        if (policies.isEmpty()) {
            logger.info("No policies found for customerId: {}", customerId);
        } else {
            logger.info("Returning {} policies for customerId: {}", policies.size(), customerId);
            logger.info("===========================");
        }
        return new ResponseEntity<>(policies, HttpStatus.OK);
    }
}