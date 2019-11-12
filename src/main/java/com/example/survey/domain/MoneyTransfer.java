package com.example.survey.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@Entity
@Table(name = "money_transfer")
public class MoneyTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private MoneyTransferEvent event;

    private String target;

    private int userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private User user;

    private String operationId;

    private String surveyId;

    @Enumerated(EnumType.ORDINAL)
    private MoneyTransferStatus status;

    private double moneyAmount;
    private Timestamp createDt;



    public MoneyTransfer() {
    }

    public MoneyTransfer(MoneyTransferEvent event, String target, int userId, MoneyTransferStatus status, double moneyAmount, Timestamp createDt) {
        this.event = event;
        this.target = target;
        this.userId = userId;
        this.status = status;
        this.moneyAmount = moneyAmount;
        this.createDt = createDt;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, event, target, userId, moneyAmount, createDt);
    }

    @Override
    public String toString() {
        return "Money Transfer Object id: " + id;
    }
}
