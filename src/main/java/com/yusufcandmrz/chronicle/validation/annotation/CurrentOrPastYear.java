package com.yusufcandmrz.chronicle.validation.annotation;

import com.yusufcandmrz.chronicle.validation.validator.CurrentOrPastYearValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrentOrPastYearValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentOrPastYear {

    String message() default "Year must be the current year or earlier";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
