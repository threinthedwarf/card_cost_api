package com.etraveli.card_cost_api.controller;

import com.etraveli.card_cost_api.model.ClearingCost;
import com.etraveli.card_cost_api.service.ClearingCostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card-cost-api/rest/v1/clearing-costs")
@RequiredArgsConstructor
public class ClearingCostController  extends BasicController {

    private final ClearingCostService clearingCostService;

    @PostMapping(value = "/insert")
    public ResponseEntity<ClearingCost> createClearingCost(@RequestBody ClearingCost clearingCost) {
        ClearingCost savedClearingCost = clearingCostService.saveClearingCost(clearingCost);
        return new ResponseEntity<>(savedClearingCost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClearingCost>> getAllClearingCosts() {
        List<ClearingCost> clearingCosts = clearingCostService.getAllClearingCosts();
        return new ResponseEntity<>(clearingCosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClearingCost> getClearingCostById(@PathVariable Long id) {
        return clearingCostService.getClearingCostById(id)
                .map(clearingCost -> new ResponseEntity<>(clearingCost, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping
    public ResponseEntity<ClearingCost> updateClearingCost(@RequestBody ClearingCost clearingCost) {
        ClearingCost updatedClearingCost = clearingCostService.updateClearingCost(clearingCost);
        return new ResponseEntity<>(updatedClearingCost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClearingCost(@PathVariable Long id) {
        clearingCostService.deleteClearingCost(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

