package com.example.survey.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int surveyId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surveyId", referencedColumnName = "id", nullable = false, updatable = false, insertable = false)
    private Survey survey;



}
