package com.etraveli.card_cost_api.service;

public class CardNumberExtractor {

    protected static final String INVALID_CARD_NUMBER_ERROR_MESSAGE = "Invalid card number.";

    protected static int extractIinFromCardNumber(String cardNumber) {
        try {
            // validate that card number is digits only
            return Integer.parseInt(cardNumber.substring(0, 6));
        } catch (NumberFormatException ex) {
            throw new RuntimeException(INVALID_CARD_NUMBER_ERROR_MESSAGE);
        }
    }
}
