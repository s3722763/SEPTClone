package com.rmit.majorproject.BackEnd.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class DateConstraintValidator implements ConstraintValidator<DateConstraint, List<String>> {
    @Override
    public boolean isValid(List<String> days, ConstraintValidatorContext context) {
        if (days == null) {
            return true;
        }

        List<String> daysAlreadyAdded = new ArrayList<>(7);

        if (days.size() > 7) {
            return false;
        }

        for (String day : days) {
            day = day.toUpperCase();

            if (!day.matches("(MON)|(TUE)|(WED)|(THU)|(FRI)|(SAT)|(SUN)|()") || daysAlreadyAdded.contains(day)) {
                return false;
            } else {
                daysAlreadyAdded.add(day);
            }
        }

        return true;
    }
}
