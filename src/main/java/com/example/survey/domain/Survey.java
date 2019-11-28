package com.example.survey.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "survey")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int limeId;

    private String title;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private Set<Ticket> tickets;


}
