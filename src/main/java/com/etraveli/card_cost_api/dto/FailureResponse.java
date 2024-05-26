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
public class FailureResponse extends ServiceResponse {
    @Serial
    private static final long serialVersionUID = -8287443043611155876L;
    private String errorMessage;
}
