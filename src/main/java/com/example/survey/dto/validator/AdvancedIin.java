package com.example.survey.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IinValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AdvancedIin {
    String message() default "Bad IIN";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}