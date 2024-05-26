package com.etraveli.card_cost_api.service;

public class CardNumberUtils {

    protected static final String INVALID_CARD_NUMBER_ERROR_MESSAGE = "Card number should be 8-19 digits.";

    protected static int extractIinFromCardNumber(String cardNumber) {
        try {
            // validate that card number is digits only
            Long.parseLong(cardNumber);
            // validate card number length
            if (cardNumber.length() < 8 || cardNumber.length() > 19) {
                throw new RuntimeException(INVALID_CARD_NUMBER_ERROR_MESSAGE);
            }
            return Integer.parseInt(cardNumber.substring(0, 6));

        } catch (NumberFormatException ex) {
            throw new RuntimeException(INVALID_CARD_NUMBER_ERROR_MESSAGE);
        }
    }
}
