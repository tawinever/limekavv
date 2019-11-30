package com.example.survey.dto.validator;

import com.example.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        //check string length
        if (iin.length() != 12) return false;

        //check string characters are digit or not
        //check 7th character is [1, 6]
        for (int i = 0; i < iin.length(); i++) {
            if (!Character.isDigit(iin.charAt(i)))
                return false;
            if (i == 6) {
                int seventhCharValue = Integer.parseInt(String.valueOf(iin.charAt(6)));
                if (seventhCharValue < 1 || seventhCharValue > 6)
                    return false;
            }
        }

        //check string format is correct and actual age of user
        try {
            String birthPart  = iin.substring(0,6);
            Date birthDate = new SimpleDateFormat("yyMMdd").parse(birthPart);
            Date today = new Date();
            long diff = today.getTime() - birthDate.getTime();
            long year = 1000L * 60L * 60L * 24L * 365L;
            long eighteenYears =  year * 18L;
            long ninetyFiveYears = year * 95L;
            if (diff < eighteenYears || diff > ninetyFiveYears)
                return false;

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}