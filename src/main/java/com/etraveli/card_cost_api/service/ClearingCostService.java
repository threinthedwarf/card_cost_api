package com.etraveli.card_cost_api.service;

import com.etraveli.card_cost_api.model.ClearingCost;
import com.etraveli.card_cost_api.repository.ClearingCostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClearingCostService {
    private final ClearingCostRepository clearingCostRepository;

    public ClearingCost saveClearingCost(ClearingCost clearingCost) {
        return clearingCostRepository.save(clearingCost);
    }

    public List<ClearingCost> getAllClearingCosts() {
        return clearingCostRepository.findAll();
    }

    public Optional<ClearingCost> getClearingCostById(Long id) {
        return clearingCostRepository.findById(id);
    }

    public Optional<ClearingCost> getClearingCostByCountry(String country) {
        List<ClearingCost> clearingCosts = clearingCostRepository.findAll();

        Optional<ClearingCost> clearingCost = clearingCosts.stream().filter(cc -> country.equalsIgnoreCase(cc.getCountry())).findFirst();

        // if a record does not exist in clearing cost matrix, check for the "Others" value
        if (clearingCost.isEmpty()) {
            clearingCost = clearingCosts.stream().filter(cc -> "Others".equalsIgnoreCase(cc.getCountry())).findFirst();
        }

        return clearingCost;
    }

    public ClearingCost updateClearingCost(ClearingCost clearingCost) {
        Optional<ClearingCost> optionalClearingCost = clearingCostRepository.findByCountry(clearingCost.getCountry());

        if (optionalClearingCost.isPresent()) {
            ClearingCost existingClearingCost = optionalClearingCost.get();
            existingClearingCost.setCountry(clearingCost.getCountry());
            existingClearingCost.setCost(clearingCost.getCost());
            return clearingCostRepository.save(existingClearingCost);
        } else {
            throw new RuntimeException("ClearingCost not found for country " + clearingCost);
        }
    }

    public void deleteClearingCost(Long id) {
        clearingCostRepository.deleteById(id);
    }
}
