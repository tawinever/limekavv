package com.example.survey.service;


import com.example.survey.domain.MoneyTransfer;
import com.example.survey.dto.StatisticMoneyTransferDto;
import com.example.survey.repository.MoneyTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    MoneyTransferRepository moneyTransferRepository;

    public List<StatisticMoneyTransferDto> getAllMoneyTransferData() {
        List<MoneyTransfer> mts = moneyTransferRepository.getAllActive();
        return mts.stream()
                .map(m -> new StatisticMoneyTransferDto(
                        m.getUser().getName(),
                        m.getUser().getEmail(),
                        m.getCreateDt().getTime(),
                        m.getTarget(),
                        m.getMoneyAmount(),
                        m.getStatus().toString()
                )).collect(Collectors.toList());
    }

    public double getTotalEarnedSum() {
        Double ans = moneyTransferRepository.getTotalEarnedSum();
        if (ans == null) return 0;
        else return ans;
    }

    public double getTotalWithdrawnSum() {
        Double ans = moneyTransferRepository.getTotalWithdrawnSum();
        if (ans == null) return 0;
        else return ans * (-1);
    }


 }
