package com.etraveli.card_cost_api.service;

import com.etraveli.card_cost_api.dto.ExternalApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExternalApiIntegrationService {

    private final RestTemplate restTemplate;

    public ExternalApiResponse getExternalApiResponse(int iin) {
        String url = "https://lookup.binlist.net/" + iin;
        return restTemplate.getForObject(url, ExternalApiResponse.class);
    }
}
