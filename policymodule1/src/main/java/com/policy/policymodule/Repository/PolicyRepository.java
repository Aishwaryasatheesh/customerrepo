package com.policy.policymodule.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.policy.policymodule.entity.Policy;


@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
	
	

	List<Policy> findPolicyByCoverageType(String coverageType);

}
