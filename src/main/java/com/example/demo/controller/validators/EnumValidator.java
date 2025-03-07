package com.example.demo.controller.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;


public class EnumValidator implements ConstraintValidator<EnumValidation, String> {
    private Enum<?>[] enumValues;

    @Override
    public void initialize(EnumValidation annotation) {
        Class<? extends Enum<?>> enumClass = annotation.enumClass();
        enumValues = enumClass.getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return Arrays.stream(enumValues)
                .anyMatch(e -> e.name().equalsIgnoreCase(value));
    }
}