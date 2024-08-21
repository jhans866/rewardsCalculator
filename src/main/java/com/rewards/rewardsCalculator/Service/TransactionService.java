package com.rewards.rewardsCalculator.Service;

import com.rewards.rewardsCalculator.Entity.RewardPoints;
import com.rewards.rewardsCalculator.Entity.Transaction;
import com.rewards.rewardsCalculator.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<RewardPoints> calculateRewardPoints(String customerId, String month) {
        List<Transaction> transactions = transactionRepository.findByCustomerIdAndMonth(customerId);
        List<RewardPoints> rewardPointsList = new ArrayList<>();
        int totalPoints = 0;

        for (Transaction transaction : transactions) {
            int points = calculatePoints(transaction.getAmount());
            totalPoints += points;
        }

        RewardPoints rewardPoints = new RewardPoints();
        rewardPoints.setCustomerId(customerId);
        rewardPoints.setPoints(totalPoints);
        rewardPoints.setMonth(month);

        rewardPointsList.add(rewardPoints);
        return rewardPointsList;
    }

    private int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
            points += 50; // 1 point per dollar for the amount between 50 and 100
        } else if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }
}
