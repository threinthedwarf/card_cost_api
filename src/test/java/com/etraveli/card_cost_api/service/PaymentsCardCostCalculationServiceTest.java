package com.etraveli.card_cost_api.service;

import com.etraveli.card_cost_api.dto.Country;
import com.etraveli.card_cost_api.dto.ExternalApiResponse;
import com.etraveli.card_cost_api.dto.PaymentsCardCostRequest;
import com.etraveli.card_cost_api.dto.PaymentsCardCostResponse;
import com.etraveli.card_cost_api.model.ClearingCost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentsCardCostCalculationServiceTest {

    @Mock
    private ExternalApiIntegrationService externalApiIntegrationService;

    @Mock
    private ClearingCostService clearingCostService;

    @InjectMocks
    private PaymentsCardCostCalculationService paymentsCardCostCalculationService;
    private PaymentsCardCostRequest request;
    private ExternalApiResponse externalApiResponse;
    private ClearingCost clearingCost;

    @BeforeEach
    void setUp() {
        request = new PaymentsCardCostRequest("1234567890123456");

        externalApiResponse = new ExternalApiResponse();
        Country countryFromExternal = new Country();
        countryFromExternal.setAlpha2("GR");
        externalApiResponse.setCountry(countryFromExternal);

        clearingCost = new ClearingCost();
        clearingCost.setCountry("GR");
        clearingCost.setCost(10D);
    }

    @Test
    public void testCalculateCost_Success() {
        when(externalApiIntegrationService.getExternalApiResponse(anyInt())).thenReturn(externalApiResponse);
        when(clearingCostService.getClearingCostByCountry("GR")).thenReturn(Optional.of(clearingCost));

        PaymentsCardCostResponse response = paymentsCardCostCalculationService.calculateCost(request);

        assertEquals("GR", response.getCountry());
        assertEquals(10, response.getCost());
    }

    @Test
    public void testCalculateCost_InvalidCardNumber() {
        request = new PaymentsCardCostRequest("invalidcardnumber");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            paymentsCardCostCalculationService.calculateCost(request);
        });

        assertEquals("Invalid card number.", exception.getMessage());
    }

    @Test
    public void testCalculateCost_NoCountryInApiResponse() {
        externalApiResponse.setCountry(new Country());
        when(externalApiIntegrationService.getExternalApiResponse(anyInt())).thenReturn(externalApiResponse);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            paymentsCardCostCalculationService.calculateCost(request);
        });

        assertEquals("Card number is invalid", exception.getMessage());
    }

    @Test
    public void testCalculateCost_NoClearingCostFound() {
        when(externalApiIntegrationService.getExternalApiResponse(anyInt())).thenReturn(externalApiResponse);
        when(clearingCostService.getClearingCostByCountry("GR")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            paymentsCardCostCalculationService.calculateCost(request);
        });

        assertEquals("No clearing cost found.", exception.getMessage());
    }
}
