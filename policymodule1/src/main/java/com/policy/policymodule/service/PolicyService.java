package com.policy.policymodule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policy.policymodule.Repository.PolicyRepository;
import com.policy.policymodule.entity.Policy;
import com.policy.policymodule.exception.PolicyNotFoundException;

@Service
public class PolicyService {
@Autowired
 private PolicyRepository policyRepository;

//add policy
public Policy addPolicy(Policy policy) {
	policy = policyRepository.save(policy);
	return policy;
}

public Policy viewpolicyById(Long policyid) throws PolicyNotFoundException {
	 Policy policy = (Policy) policyRepository.findById(policyid).orElse(null);
	 if(policy==null)
		 throw  new PolicyNotFoundException("Policy Id not found");
	 return policy;

}

public List<Policy> getPoliciesByCoverageType(String coverageType) {
    return policyRepository.findPolicyByCoverageType(coverageType);
}
}



