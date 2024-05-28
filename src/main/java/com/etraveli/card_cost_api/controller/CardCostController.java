package com.etraveli.card_cost_api.controller;

import com.etraveli.card_cost_api.dto.PaymentsCardCostRequest;
import com.etraveli.card_cost_api.dto.PaymentsCardCostResponse;
import com.etraveli.card_cost_api.service.PaymentsCardCostCalculationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card-cost-api/rest/v1/payment-cards-cost")
public class CardCostController extends BaseController {
    private final PaymentsCardCostCalculationService paymentsCardCostCalculationService;

    public CardCostController(PaymentsCardCostCalculationService paymentsCardCostCalculationService) {
        this.paymentsCardCostCalculationService = paymentsCardCostCalculationService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public PaymentsCardCostResponse paymentCardsCost(@Valid @RequestBody PaymentsCardCostRequest request) {
        return paymentsCardCostCalculationService.calculateCost(request);
    }
}
