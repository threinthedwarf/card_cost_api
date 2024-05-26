package com.etraveli.card_cost_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class PaymentsCardCostResponse extends ServiceResponse {
    @Serial
    private static final long serialVersionUID = 8994026500875539647L;
    private String country;
    private double cost;
}
