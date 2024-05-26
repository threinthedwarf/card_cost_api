package com.etraveli.card_cost_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalApiResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 2129747257598546019L;
    private String country;

    @JsonProperty("country")
    public void setCountry(Country country) {
        this.country = country.getAlpha2();
    }

}
