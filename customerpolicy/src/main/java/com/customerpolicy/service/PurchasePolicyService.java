package com.customerpolicy.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PurchasePolicyService {
    @Autowired
    private CustomerRepository customerRepository;
@Autowired
private RestTemplate restTemplate;
    @Autowired
    private PurchaseRepository purchasedPolicyRepository;

    private final String policyServiceUrl = "http://localhost:8763/app1/policy"; // Adjust URL as needed

    public PurchasedPolicy purchasePolicy(Long customerId, Long policyId, PurchasePolicyRequest request) throws Exception {
        // Fetch customer details
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Fetch policy details using RestTemplate
        Policy policy = restTemplate.getForObject(policyServiceUrl + "/" + policyId, Policy.class);
        if (policy == null) {
            throw new RuntimeException("Policy not found");
        }

        // Create and save the purchased policy
        PurchasedPolicy purchasedPolicy = new PurchasedPolicy();
        purchasedPolicy.setCustomer(customer);
        purchasedPolicy.setPolicyId(policyId);
        purchasedPolicy.setPurchaseDate(LocalDateTime.now());
        purchasedPolicy.setPremium(request.getPremium());
        purchasedPolicy.setMaturityDate(request.getMaturityDate());
        purchasedPolicy.setStatus("Active");

        return purchasedPolicyRepository.save(purchasedPolicy);
    }
    public List<PurchasedPolicy> getPurchasedPoliciesByCustomerId(Long customerId) {
        return PurchaseRepository.findByCustomerCustomerId(customerId);
    }
}