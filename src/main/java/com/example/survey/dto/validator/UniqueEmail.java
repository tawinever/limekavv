package com.example.survey.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserEmailValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "User with such email already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}