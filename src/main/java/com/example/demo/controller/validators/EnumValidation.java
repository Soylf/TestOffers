package com.example.demo.controller.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidation {
    Class<? extends Enum<?>> enumClass();
    String message() default "Что-то пошло не так";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
