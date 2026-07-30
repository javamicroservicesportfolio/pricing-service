package com.portfolio.pricingservice.repository;

import com.portfolio.pricingservice.model.FareRules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FareRulesRepository extends JpaRepository<FareRules, Long> {
    FareRules findByFareId(Long fareId);

    boolean existsByFareId(Long fareId);

    List<FareRules> getFareRulesByAirlineId(Long airlineId);
}
