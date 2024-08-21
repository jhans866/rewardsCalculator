package com.rewards.rewardsCalculator.Controller;

import com.rewards.rewardsCalculator.Entity.RewardPoints;
import com.rewards.rewardsCalculator.Entity.Transaction;
import com.rewards.rewardsCalculator.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TranactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @GetMapping("/reward-points")
    public List<RewardPoints> getRewardPoints(@RequestParam String customerId, @RequestParam String month) {
        return transactionService.calculateRewardPoints(customerId, month);
    }

}
