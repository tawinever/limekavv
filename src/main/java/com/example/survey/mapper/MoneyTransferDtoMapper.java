package com.example.survey.mapper;

import com.example.survey.domain.MoneyTransfer;
import com.example.survey.dto.MoneyTransferDto;

public class MoneyTransferDtoMapper {
    public static MoneyTransferDto convert(MoneyTransfer mt) {
        return  new MoneyTransferDto(
                mt.getId(),
                mt.getEvent().toString(),
                mt.getTarget(),
                mt.getCreateDt(),
                mt.getMoneyAmount()
        );
    }
}
