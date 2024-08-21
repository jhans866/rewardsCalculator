package com.rewards.rewardsCalculator.Repository;

import com.rewards.rewardsCalculator.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public class List<Customer> implements JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction y WHERE Date_Column between DATEADD(MONTH, -3, GETDATE()) and GETDATE() and customerId = custid")
    public List<Transaction> findByCustomerIdAndMonth(String custid);

}
