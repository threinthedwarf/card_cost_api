package com.etraveli.card_cost_api.service;

import com.etraveli.card_cost_api.model.ClearingCost;
import com.etraveli.card_cost_api.repository.ClearingCostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClearingCostServiceTest {

    @Mock
    private ClearingCostRepository clearingCostRepository;
    @InjectMocks
    private ClearingCostService clearingCostService;
    private ClearingCost clearingCostGr;
    private ClearingCost clearingCostOthers;

    @BeforeEach
    void setUp() {
        clearingCostGr = new ClearingCost();
        clearingCostGr.setId(1L);
        clearingCostGr.setCountry("GR");
        clearingCostGr.setCost(100D);

        clearingCostOthers = new ClearingCost();
        clearingCostOthers.setId(2L);
        clearingCostOthers.setCountry("Others");
        clearingCostOthers.setCost(300D);
    }

    @Test
    public void testGetClearingCostByCountry_CountryNotExist() {
        when(clearingCostRepository.findAll()).thenReturn(Arrays.asList(clearingCostGr, clearingCostOthers));
        Optional<ClearingCost> result = clearingCostService.getClearingCostByCountry("US");
        assertEquals(clearingCostOthers, result.orElse(null));
    }

    @Test
    public void testGetClearingCostByCountry_CountryExists() {
        when(clearingCostRepository.findAll()).thenReturn(Arrays.asList(clearingCostGr, clearingCostOthers));
        Optional<ClearingCost> result = clearingCostService.getClearingCostByCountry("GR");
        assertEquals(clearingCostGr, result.orElse(null));
    }

    @Test
    public void testGetClearingCostByCountry_CountryExistsCaseInsensitive() {
        when(clearingCostRepository.findAll()).thenReturn(Arrays.asList(clearingCostGr, clearingCostOthers));
        Optional<ClearingCost> result = clearingCostService.getClearingCostByCountry("Gr");
        assertEquals(clearingCostGr, result.orElse(null));
    }

    @Test
    public void testUpdateClearingCost_CountryExists() {
        ClearingCost requestedClearingCost = new ClearingCost();
        requestedClearingCost.setCountry("GR");
        requestedClearingCost.setCost(10D);

        when(clearingCostRepository.findByCountry("GR")).thenReturn(Optional.ofNullable(clearingCostGr));

        clearingCostService.updateClearingCost(requestedClearingCost);

        verify(clearingCostRepository, times(1)).save(argThat(cc -> cc.getId().equals(clearingCostGr.getId())));
    }

    @Test
    public void testUpdateClearingCost_CountryNotExist() {
        ClearingCost requestedClearingCost = new ClearingCost();
        requestedClearingCost.setCountry("US");
        requestedClearingCost.setCost(10D);

        when(clearingCostRepository.findByCountry("US")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            clearingCostService.updateClearingCost(requestedClearingCost);
        });
        assertTrue(exception.getMessage().contains("ClearingCost not found for country " + requestedClearingCost));
    }

}
