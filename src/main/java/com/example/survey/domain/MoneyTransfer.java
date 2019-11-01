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

    private int user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private User user;

    private int moneyAmount;
    private Timestamp createDt;

    public MoneyTransfer() {
    }

    public MoneyTransfer(MoneyTransferEvent event, String target, int user_id, int moneyAmount, Timestamp createDt) {
        this.event = event;
        this.target = target;
        this.user_id = user_id;
        this.moneyAmount = moneyAmount;
        this.createDt = createDt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, target, user_id, moneyAmount, createDt);
    }

    @Override
    public String toString() {
        return "Wa";
    }
}
