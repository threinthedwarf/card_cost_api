package com.etraveli.card_cost_api.service;

import com.etraveli.card_cost_api.dto.ExternalApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest
public class ExternalApiIntegrationServiceTest {

    @Autowired
    private ExternalApiIntegrationService externalApiIntegrationService;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void testGetExternalData() {
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

        int iin = 123123;

        mockServer.expect(requestTo("https://lookup.binlist.net/" + iin))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"number\":{\"length\":16,\"luhn\":true},\"scheme\":\"visa\",\"type\":\"debit\",\"brand\":\"Visa/Dankort\",\"prepaid\":false,\"country\":{\"numeric\":\"208\",\"alpha2\":\"DK\",\"name\":\"Denmark\",\"emoji\":\"\uD83C\uDDE9\uD83C\uDDF0\",\"currency\":\"DKK\",\"latitude\":56,\"longitude\":10},\"bank\":{\"name\":\"Jyske Bank\",\"url\":\"www.jyskebank.dk\",\"phone\":\"+4589893300\",\"city\":\"Hjørring\"}}"));

        ExternalApiResponse response = externalApiIntegrationService.getExternalApiResponse(iin);

        assertEquals("DK",response.getCountry());

        mockServer.verify();
    }
}
