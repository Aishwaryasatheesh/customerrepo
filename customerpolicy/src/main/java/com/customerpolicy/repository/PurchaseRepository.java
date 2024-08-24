package com.customerpolicy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customerpolicy.entity.PurchasedPolicy;
@Repository
	public interface PurchaseRepository extends JpaRepository<PurchasedPolicy, Long> {
	List<PurchasedPolicy> findByCustomerCustomerId(Long customerId);
	}
