package com.yusufcandmrz.chronicle.validation.validator;

import com.yusufcandmrz.chronicle.validation.annotation.CurrentOrPastYear;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class CurrentOrPastYearValidator implements ConstraintValidator<CurrentOrPastYear, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value <= Year.now().getValue();
    }
}
