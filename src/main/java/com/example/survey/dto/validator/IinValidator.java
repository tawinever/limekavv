package com.example.survey.dto.validator;

import com.example.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IinValidator implements
        ConstraintValidator<AdvancedIin, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(AdvancedIin iin) {
    }

    @Override
    public boolean isValid(String iin,
                           ConstraintValidatorContext cxt) {
        if (iin.length() != 12) return false;
        String normalized = "";

        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("yyMMyyyy").parse(sDate1);
        //neeed codes to check everything
//        return emailField == null || userRepository.findByEmail(emailField) == null;
    }

}