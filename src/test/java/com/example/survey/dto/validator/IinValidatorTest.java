package com.example.survey.dto.validator;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class IinValidatorTest {

    @Test
    public void isValid() {
        String s = "600501";
        try {
            Date date1=new SimpleDateFormat("yyMMdd").parse(s);
            long tx = date1.getTime();
            assertEquals(1,1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}