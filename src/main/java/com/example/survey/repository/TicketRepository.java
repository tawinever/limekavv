package com.example.survey.repository;

import com.example.survey.domain.Ticket;
import com.example.survey.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
