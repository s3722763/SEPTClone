package com.rmit.majorproject.BackEnd.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = DateConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateConstraint {
    String message() default "Invalid format for Date/s";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
