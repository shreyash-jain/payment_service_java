package com.ppa.Payment.Service.repository;


import com.ppa.Payment.Service.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {


    // todo : add db constraints so that the amount does not go below 0
    @Modifying
    @Query("update Account a set a.amount = a.amount + :amount where a.accountId = :senderAccountId")
    void addBalance(Long senderAccountId, Double amount);


    @Modifying
    @Query("update Account a set a.amount = a.amount - :amount where a.accountId = :receiverAccountId")
    void removeBalance(Long receiverAccountId, Double amount);
}
