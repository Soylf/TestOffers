package com.example.demo.controller.validators.priority;

import com.example.demo.model.enums.Priority;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PriorityValidator implements ConstraintValidator<PriorityValidation, String> {

    private static final Set<String> ALLOWED_VALUES = EnumSet.allOf(Priority.class)
            .stream()
            .map(Enum::name)
            .collect(Collectors.toSet());

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return ALLOWED_VALUES.contains(value);
    }
}
