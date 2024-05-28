package com.etraveli.card_cost_api.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString(callSuper = true)
public class PaymentsCardCostRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 2900947566923758502L;
    @NotNull(message = "Card number is null.")
    @NotEmpty(message = "Card number is empty")
    @Range(max = 19, min = 8, message = "Card number should be between 8 to 19 digits")
    private String cardNumber;
}
