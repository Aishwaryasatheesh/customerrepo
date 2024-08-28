package com.policy.policymodule.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.policy.policymodule.Repository.PolicyRepository;
import com.policy.policymodule.beans.ApiResponse;
import com.policy.policymodule.entity.Policy;
import com.policy.policymodule.exception.PolicyNotFoundException;
import com.policy.policymodule.service.PolicyService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/policy")
public class PolicyController {
	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);

	
	@Autowired
	 private PolicyService policyService;

	 @PostMapping
	    public ResponseEntity<Policy> addPolicy(@Valid @RequestBody Policy policy) {
		 logger.info("=================");
		 logger.info("addPolicy() called  from controller layer");
		policy = policyService.addPolicy(policy);
		logger.info("==================");
		logger.info("policy = {}",policy);
		logger.info("addPolicy() is ended from controller layer");
	        return new ResponseEntity<Policy>(policy, HttpStatus.OK);
	    }
	 @GetMapping("/{policyId}")
	    public ResponseEntity<Policy> viewPolicyById(@PathVariable("policyId") Long policyId) throws PolicyNotFoundException {
		 logger.info("==========================");
		 logger.info("viewPolicyById() called  from controller layer");
			Policy policy = policyService.viewpolicyById(policyId);
			logger.info("==========================");
			 logger.info("policyId: {}", policyId);
			 logger.info("Policy retrieved: {}", policy);
	        return new  ResponseEntity<Policy>(policy,HttpStatus.OK);
	    }
	 @GetMapping("/category/{coveragetype}")
	    public ResponseEntity<List<Policy>> getPoliciesByCoverageType(@PathVariable String coveragetype) throws PolicyNotFoundException {
		 logger.info("==========================");
		 logger.info("getPoliciesByCoverageType() called with coverageType: {}", coveragetype);
	        List<Policy> policies = policyService.getPoliciesByCoverageType(coveragetype);
	        logger.info("Returning {} policies for coverageType: {}", policies.size(), coveragetype);
	        logger.info("==========================");
	        return ResponseEntity.ok(policies);
	    }
	
}










