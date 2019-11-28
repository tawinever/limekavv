package com.example.survey.service;

import com.example.survey.domain.MoneyTransfer;
import com.example.survey.domain.MoneyTransferEvent;
import com.example.survey.domain.MoneyTransferStatus;
import com.example.survey.domain.User;
import com.example.survey.dto.MobileWithdrawalDto;
import com.example.survey.dto.MoneyTransferDto;
import com.example.survey.mapper.MoneyTransferDtoMapper;
import com.example.survey.repository.MoneyTransferRepository;
import com.example.survey.repository.UserRepository;
import com.example.survey.withdrawal.WithdrawalService;
import com.example.survey.withdrawal.exception.CannotAuthenticateException;
import com.example.survey.withdrawal.exception.NotEnoughMoneyException;
import com.example.survey.withdrawal.exception.RefusedPaymentException;
import com.example.survey.withdrawal.exception.ServiceBusyException;
import com.example.survey.withdrawal.model.response.PaymentProcessing;
import com.example.survey.withdrawal.model.wallet.MobileWallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MoneyTransferService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MoneyTransferRepository moneyTransferRepository;

    @Autowired
    WithdrawalService withdrawalService;



    public List<MoneyTransferDto> getMoneyTransfer(String userEmail) {
        return getMoneyTransferPrivate(userEmail).stream()
                .map(MoneyTransferDtoMapper::convert)
                .collect(Collectors.toList());
    }

    public Double getBalance(String userEmail) {
        return getMoneyTransferPrivate(userEmail).stream().mapToDouble(MoneyTransfer::getMoneyAmount).sum();
    }

    //workaround for caching problem.
    //since spring cache getBalance before withdrawal, we need some how purge cache but I dont have time to research it
    public Double hetBalance(String userEmail) {
        User userDomain = userRepository.findByEmail(userEmail);
        return moneyTransferRepository.getBalance(userDomain.getId().intValue());
    }

    private List<MoneyTransfer> getMoneyTransferPrivate(String userEmail) {
        User domainUser = userRepository.findByEmail(userEmail);
        return moneyTransferRepository.findByUserId(domainUser.getId().intValue()).stream()
                .filter(bill -> bill.getStatus()!= MoneyTransferStatus.FAILED)
                .collect(Collectors.toList());
    }

    @Transactional
    public MoneyTransfer createWithdrawalDraft(String userEmail, MobileWithdrawalDto withdrawalDto) throws ServiceBusyException, NotEnoughMoneyException {
        log.info("IN MoneyTransferService - createdWithdrawalDraft");

        //set exclusive access to table namely wait till you are able to get it
        moneyTransferRepository.lockWithdrawalTable();

        //check user withdrawal feature is blocked or not
        boolean isWithdrawalBlocked = moneyTransferRepository.isUserAllowedToWithdraw(userEmail) != 0;
        if (isWithdrawalBlocked)
            throw new ServiceBusyException("Currently System is trying to handle your previous transaction");

        //check current balance >= withdrawing sum
        if (withdrawalDto.getAmount() > getBalance(userEmail)) {
            throw new NotEnoughMoneyException("You dont have so much money");
        }

        //decide to process withdrawal. block cur user withdrawal feature.
        try {
            moneyTransferRepository.blockWithdrawal(userEmail); //blocked
        } catch (Exception e) {
            throw new ServiceBusyException("Could not create blocking record. Try later.");
        }

        //creating money transfer object
        User domainUser = userRepository.findByEmail(userEmail);
        MoneyTransfer mt = new MoneyTransfer(
                MoneyTransferEvent.WITHDRAW,
                withdrawalDto.toInlineString(),
                domainUser.getId().intValue(),
                MoneyTransferStatus.IN_PROCESS,
                withdrawalDto.getAmount() * (-1),
                new Timestamp((new Date()).getTime())
        );
        return moneyTransferRepository.save(mt);
    }

    public void withdraw(String userEmail, MobileWithdrawalDto withdrawalDto) throws NotEnoughMoneyException, ServiceBusyException, RefusedPaymentException, CannotAuthenticateException {
        log.info("IN MoneyTransferService - withdraw");

        //creating blocked sum(aka in process) withdrawal in order to send it.
        MoneyTransfer mt = createWithdrawalDraft(userEmail, withdrawalDto);
        moneyTransferRepository.removeWithdrawalBlock(userEmail);
        try {
            PaymentProcessing pp = withdrawalService.sendMoney(
                    new MobileWallet(
                            withdrawalDto.getOperator(),
                            withdrawalDto.formattedPhoneNumber()),
                    withdrawalDto.getAmount().floatValue()
            );
            mt.setOperationId(pp.getProcessId());
            moneyTransferRepository.save(mt);
        } catch (Exception | RefusedPaymentException | CannotAuthenticateException e) {
            mt.setStatus(MoneyTransferStatus.FAILED);
            moneyTransferRepository.save(mt);
            throw e;
        }

    }


}
