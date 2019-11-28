package com.example.survey.repository;

import com.example.survey.domain.Survey;
import com.example.survey.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Survey findByLimeId(int limeId);
}
