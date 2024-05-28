package com.etraveli.card_cost_api.service;

import com.etraveli.card_cost_api.dto.ExternalApiResponse;
import com.etraveli.card_cost_api.dto.PaymentsCardCostRequest;
import com.etraveli.card_cost_api.dto.PaymentsCardCostResponse;
import com.etraveli.card_cost_api.model.ClearingCost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentsCardCostCalculationService {
    private final ExternalApiIntegrationService externalApiIntegrationService;
    private final ClearingCostService clearingCostService;

    public PaymentsCardCostResponse calculateCost(PaymentsCardCostRequest request) {

        int iin = CardNumberExtractor.extractIinFromCardNumber(request.getCardNumber());

        ExternalApiResponse externalApiResponse = externalApiIntegrationService.getExternalApiResponse(iin);

        if (externalApiResponse.getCountry() == null) {
            throw new RuntimeException("Card number is invalid");
        }

        Optional<ClearingCost> clearingCost = clearingCostService.getClearingCostByCountry(externalApiResponse.getCountry());

        if (clearingCost.isEmpty()) {
            throw new RuntimeException("No clearing cost found.");
        }

        return new PaymentsCardCostResponse(externalApiResponse.getCountry(), clearingCost.get().getCost());
    }
}
