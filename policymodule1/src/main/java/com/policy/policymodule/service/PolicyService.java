package com.policy.policymodule.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policy.policymodule.Repository.PolicyRepository;
import com.policy.policymodule.entity.Policy;
import com.policy.policymodule.exception.PolicyNotFoundException;

import jakarta.persistence.criteria.From;

@Service
public class PolicyService {
@Autowired
 private PolicyRepository policyRepository;
Logger logger = LoggerFactory.getLogger(PolicyService.class);

public Policy addPolicy(Policy policy) {
	logger.info("===============================");
	logger.info("Saving new policy: {}", policy);
	policy = policyRepository.save(policy);
	logger.info("Policy successfully saved: {}", policy);
	logger.info("===============================");
	return policy;
}

public Policy viewpolicyById(Long policyid) throws PolicyNotFoundException {
	logger.info("viewpolicyById() called with policyId: {}", policyid);
	 Policy policy = (Policy) policyRepository.findById(policyid).orElse(null);
	 if(policy==null) {
		 logger.error("Policy not found with policyId: {}", policyid);
		 throw  new PolicyNotFoundException("Policy Id not found");
	 }
		 logger.info("Policy found: {}", policy);
	 return policy;

}

public List<Policy> getPoliciesByCoverageType(String coverageType) throws PolicyNotFoundException {
    logger.info("Fetching policies with coverageType: {}", coverageType);
    List<Policy> policies = policyRepository.findPolicyByCoverageType(coverageType);
    if (policies.isEmpty()) {
        logger.error("No policies found with coverageType: {}", coverageType);
        throw new PolicyNotFoundException("Coverage type not found");
    }
    logger.info("Found {} policies with coverageType: {}", policies.size(), coverageType);
    return policies;
}

}


