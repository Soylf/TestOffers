package com.example.demo.controller.validators.priority;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PriorityValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PriorityValidation {
    String message() default "Недопустимое значение приоритета. Разрешены только: HIGH, MEDIUM, LOW";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}