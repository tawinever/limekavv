package com.example.survey.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "usr")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String surname;
    private String name;
    private String middlename;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private String phone;
    private String phone2;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<MoneyTransfer> bills;

    public User() {
    }

    public User(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = Role.values()[0];
        this.status = Status.ACTIVE;
    }
}
