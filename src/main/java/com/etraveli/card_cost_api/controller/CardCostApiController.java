package com.etraveli.card_cost_api.controller;

import com.etraveli.card_cost_api.dto.FailureResponse;
import com.etraveli.card_cost_api.dto.PaymentsCardCostRequest;
import com.etraveli.card_cost_api.dto.PaymentsCardCostResponse;
import com.etraveli.card_cost_api.dto.ServiceResponse;
import com.etraveli.card_cost_api.service.ClearingCostService;
import com.etraveli.card_cost_api.service.PaymentsCardCostCalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/card-cost-api/rest/v1/payment-cards-cost")
public class CardCostApiController extends BasicController {
    private final PaymentsCardCostCalculationService paymentsCardCostCalculationService;

    public CardCostApiController(PaymentsCardCostCalculationService paymentsCardCostCalculationService, ClearingCostService clearingCostService) {
        this.paymentsCardCostCalculationService = paymentsCardCostCalculationService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public PaymentsCardCostResponse paymentCardsCost(@RequestBody PaymentsCardCostRequest request) {
        return paymentsCardCostCalculationService.calculateCost(request);
    }
}
