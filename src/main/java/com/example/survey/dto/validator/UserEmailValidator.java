package com.example.survey.dto.validator;

import com.example.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserEmailValidator implements
        ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(UniqueEmail userEmail) {
    }

    @Override
    public boolean isValid(String emailField,
                           ConstraintValidatorContext cxt) {
        return emailField == null || userRepository.findByEmail(emailField) == null;
    }

}