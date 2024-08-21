package com.rewards.rewardsCalculator;
import com.rewards.rewardsCalculator.Entity.Transaction;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionControllerTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private RewardCalculatorService rewardCalculatorService;

    @InjectMocks
    private TransactionController controller;

    @Test
    public void testGetPointsForMonth() {
        MockitoAnnotations.openMocks(this);

        when(transactionRepository.findByCustomerIdAndDateBetween(anyLong(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Arrays.asList(new Transaction(/* fill with mock data */)));

        when(rewardCalculatorService.calculatePoints(any(BigDecimal.class)))
                .thenReturn(90);

        int points = controller.getPointsForMonth(1L, "2024-08");
        assertEquals(90, points);
    }

    @Test
    public void testGetTotalPoints() {
        MockitoAnnotations.openMocks(this);

        when(transactionRepository.findByCustomerId(anyLong()))
                .thenReturn(Arrays.asList(new Transaction(/* fill with mock data */)));

        when(rewardCalculatorService.calculatePoints(any(BigDecimal.class)))
                .thenReturn(90);

        int points = controller.getTotalPoints(1L);
        assertEquals(90, points);
    }
}