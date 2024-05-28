package com.etraveli.card_cost_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClearingCost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Country should not be empty.")
    @NotNull(message = "Country should not be null.")
    private String country;
    @Min(value = 1, message = "Cost should be greater than zero.")
    @NotNull(message = "Cost should not be null.")
    private Double cost;
}
