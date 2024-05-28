package com.etraveli.card_cost_api.service;

import org.junit.jupiter.api.Test;

import static com.etraveli.card_cost_api.service.CardNumberExtractor.INVALID_CARD_NUMBER_ERROR_MESSAGE;
import static com.etraveli.card_cost_api.service.CardNumberExtractor.extractIinFromCardNumber;
import static org.junit.jupiter.api.Assertions.*;

public class CardNumberExtractorTest {

    @Test
    public void testExtractIinFromCardNumber_ValidCardNumber() {
        String cardNumber = "1234567890123456";
        int expectedIin = 123456;
        assertEquals(expectedIin, extractIinFromCardNumber(cardNumber));
    }

    @Test
    public void testExtractIinFromCardNumber_InvalidCardNumber() {
        String cardNumber = "1234abcd5678";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            extractIinFromCardNumber(cardNumber);
        });
        assertTrue(exception.getMessage().contains(INVALID_CARD_NUMBER_ERROR_MESSAGE));
    }
}
