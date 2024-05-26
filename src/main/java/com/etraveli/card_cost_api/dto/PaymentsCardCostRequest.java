package com.etraveli.card_cost_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString(callSuper = true)
public class PaymentsCardCostRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 2900947566923758502L;
    private String cardNumber;
}
