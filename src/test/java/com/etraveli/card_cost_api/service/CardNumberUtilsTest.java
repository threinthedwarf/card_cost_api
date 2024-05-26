package com.etraveli.card_cost_api.service;

import org.junit.jupiter.api.Test;

import static com.etraveli.card_cost_api.service.CardNumberUtils.INVALID_CARD_NUMBER_ERROR_MESSAGE;
import static com.etraveli.card_cost_api.service.CardNumberUtils.extractIinFromCardNumber;
import static org.junit.jupiter.api.Assertions.*;

public class CardNumberUtilsTest {

    @Test
    public void testExtractIinFromCardNumber_ValidCardNumber() {
        String cardNumber = "1234567890123456";
        int expectedIin = 123456;
        assertEquals(expectedIin, extractIinFromCardNumber(cardNumber));
    }

    @Test
    public void testExtractIinFromCardNumber_ShortCardNumber() {
        String cardNumber = "1234567";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            extractIinFromCardNumber(cardNumber);
        });
        assertTrue(exception.getMessage().contains(INVALID_CARD_NUMBER_ERROR_MESSAGE));
    }

    @Test
    public void testExtractIinFromCardNumber_LongCardNumber() {
        String cardNumber = "12345678901234567890";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            extractIinFromCardNumber(cardNumber);
        });
        assertTrue(exception.getMessage().contains(INVALID_CARD_NUMBER_ERROR_MESSAGE));
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
