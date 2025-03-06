package com.example.demo.controller.validators.status;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StatusValidator.class) // Указываем валидатор
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusValidation {
    String message() default "Недопустимый статус. Разрешены только: PENDING, IN_PROGRESS, COMPLETED";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}