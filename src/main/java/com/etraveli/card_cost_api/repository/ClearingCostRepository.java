package com.etraveli.card_cost_api.repository;

import com.etraveli.card_cost_api.model.ClearingCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClearingCostRepository extends JpaRepository<ClearingCost, Long> {
    Optional<ClearingCost> findByCountry(String country);
}
