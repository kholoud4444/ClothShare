package com.ntg.backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EgyptianNationalIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEgyptianNationalId {
    String message() default "Invalid Egyptian National ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
