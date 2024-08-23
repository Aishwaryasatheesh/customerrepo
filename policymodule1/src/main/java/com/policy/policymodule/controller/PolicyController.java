package com.policy.policymodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.policy.policymodule.Repository.PolicyRepository;
import com.policy.policymodule.beans.ApiResponse;
import com.policy.policymodule.entity.Policy;
import com.policy.policymodule.exception.PolicyNotFoundException;
import com.policy.policymodule.service.PolicyService;

@RestController
@RequestMapping("/policy")
public class PolicyController {
	@Autowired
	 private PolicyService policyService;

	 @PostMapping
	    public ResponseEntity<Policy> addPolicy(@RequestBody Policy policy) {
		policy = policyService.addPolicy(policy);
	        return new ResponseEntity<Policy>(policy, HttpStatus.OK);
	    }
	 @GetMapping("/view/{policyId}")
	    public ResponseEntity<Policy> viewPolicyById(@PathVariable("policyId") Long policyId) throws PolicyNotFoundException {
			Policy policy = policyService.viewpolicyById(policyId);
	        return new  ResponseEntity<Policy>(policy,HttpStatus.OK);
	    }
	 @GetMapping("/view{coveragetype}")
	    public ResponseEntity<List<Policy>> getPoliciesByCoverageType(@PathVariable String coveragetype) {
	        return ResponseEntity.ok(policyService.getPoliciesByCoverageType(coveragetype));
	    }
}










