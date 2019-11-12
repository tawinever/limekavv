package com.example.survey.repository;

import com.example.survey.domain.MoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MoneyTransferRepository extends JpaRepository<MoneyTransfer, Long> {

    @Transactional
    @Modifying
    @Query(value = "LOCK TABLE withdrawal_status IN ACCESS EXCLUSIVE MODE;", nativeQuery = true)
    void lockWithdrawalTable();

    @Transactional
    @Query(value = "SELECT count(*) FROM withdrawal_status WHERE user_email = ?1", nativeQuery = true)
    Long isUserAllowedToWithdraw(String userEmail);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO withdrawal_status(user_email) VALUES (?1);", nativeQuery = true)
    void blockWithdrawal(String userEmail);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM withdrawal_status WHERE user_email = ?1", nativeQuery = true)
    void removeWithdrawalBlock(String userEmail);

    @Query(value = "SELECT SUM(money_amount) FROM money_transfer WHERE status<>0 AND user_id = ?1", nativeQuery = true)
    Double getBalance(int userId);

    @Query(value = "SELECT * FROM money_transfer WHERE status<>0", nativeQuery = true)
    List<MoneyTransfer> getAllActive();

    @Query(value = "SELECT SUM(money_amount) FROM money_transfer WHERE status<>0 AND money_amount > 0", nativeQuery = true)
    Double getTotalEarnedSum();

    @Query(value = "SELECT SUM(money_amount) FROM money_transfer WHERE status<>0 AND money_amount < 0", nativeQuery = true)
    Double getTotalWithdrawnSum();

    List<MoneyTransfer> findByUserId(int userId);
}
