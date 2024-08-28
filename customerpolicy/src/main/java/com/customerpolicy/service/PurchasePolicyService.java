package com.customerpolicy.service;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.customerpolicy.bean.Policy;
import com.customerpolicy.bean.PurchasePolicyRequest;
import com.customerpolicy.entity.Customer;
import com.customerpolicy.entity.PurchasedPolicy;
import com.customerpolicy.exception.CustomerNotFoundException;
import com.customerpolicy.exception.PolicyNotFoundException;
import com.customerpolicy.repository.CustomerRepository;
import com.customerpolicy.repository.PurchaseRepository;
import com.google.common.util.concurrent.CycleDetectingLockFactory.Policies;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PurchasePolicyService {
	private static final Logger logger = LoggerFactory.getLogger(PurchasePolicyService.class);
    @Autowired
    private CustomerRepository customerRepository;
@Autowired
private RestTemplate restTemplate;
    @Autowired
    private PurchaseRepository purchasedPolicyRepository;

    private final String policyServiceUrl = "http://localhost:8763/app1/policy"; 

    public PurchasedPolicy purchasePolicy(PurchasePolicyRequest request) throws Exception {
        Long customerId = request.getCustomerId();
        Long policyId = request.getPolicyId();

        logger.info("purchasePolicy() called with customerId: {} and policyId: {}", customerId, policyId);
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        logger.info("Customer found: {}", customer);

        Policy policy = restTemplate.getForObject(policyServiceUrl + "/" + policyId, Policy.class);
        if (policy == null) {
            throw new RuntimeException("Policy not found");
        }
        logger.info("Policy found: {}", policy);
        PurchasedPolicy purchasedPolicy = new PurchasedPolicy();
        purchasedPolicy.setCustomer(customer);
        purchasedPolicy.setPolicyId(policyId);
        purchasedPolicy.setPurchaseDate(LocalDateTime.now());
        purchasedPolicy.setPremium(request.getPremium());
        purchasedPolicy.setMaturityDate(request.getMaturityDate());
        purchasedPolicy.setStatus("Active");
        logger.info("Successfully created PurchasedPolicy: {}", purchasedPolicy);
        PurchasedPolicy savedPolicy = purchasedPolicyRepository.save(purchasedPolicy);
        logger.info("Successfully purchased policy and saved to database: {}", savedPolicy);

        return savedPolicy;
    }
    
    public List<PurchasedPolicy> viewOwnPolicy(Long customerId) throws CustomerNotFoundException {
    	logger.info("Service method viewOwnPolicy() called with customerId: {}", customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        logger.error("Customer not found with customerId: {}", customerId);
        logger.info("Customer found: {}", customer);
        List<PurchasedPolicy> policies = purchasedPolicyRepository.findByCustomer(customer);
        if (policies.isEmpty()) {
        	logger.info("No policies found for customerId: {}", customerId);
            return Collections.emptyList(); 
            
        }
        logger.info("Found {} policies for customerId: {}", policies.size(), customerId);
        return policies.stream()
                .map(policy -> {
                    
                    PurchasedPolicy responsePolicy = new PurchasedPolicy();
                    responsePolicy.setPurchasedId(policy.getPurchasedId());
                    responsePolicy.setPolicyId(policy.getPolicyId());
                    responsePolicy.setPremium(policy.getPremium());
                    responsePolicy.setMaturityDate(policy.getMaturityDate());
                    responsePolicy.setPurchaseDate(policy.getPurchaseDate());
                    responsePolicy.setStatus(policy.getStatus());
                    return responsePolicy;
                })
                .collect(Collectors.toList());
}
    }

