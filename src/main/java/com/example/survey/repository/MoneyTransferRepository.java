package com.example.survey.repository;

import com.example.survey.domain.MoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyTransferRepository extends JpaRepository<MoneyTransfer, Long> {
}
