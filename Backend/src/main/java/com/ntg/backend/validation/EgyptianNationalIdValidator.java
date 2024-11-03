package com.ntg.backend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EgyptianNationalIdValidator implements ConstraintValidator<ValidEgyptianNationalId, String> {

    @Override
    public void initialize(ValidEgyptianNationalId constraintAnnotation) {}

    @Override
    public boolean isValid(String nationalId, ConstraintValidatorContext context) {
        // Check if the national ID is 14 digits long
        if (nationalId == null || !nationalId.matches("\\d{14}")) {
            return false;
        }

        // Check if the first digit is either 2 or 3
        char century = nationalId.charAt(0);
        if (century != '2' && century != '3') {
            return false;
        }

        // Extract and validate the birth date (second to seventh digits)
        String yearPrefix = (century == '2') ? "19" : "20";
        String birthDateStr = yearPrefix + nationalId.substring(1, 7);

        try {
            LocalDate birthDate = LocalDate.parse(birthDateStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
            if (birthDate.isAfter(LocalDate.now())) {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false; // Invalid date format
        }

        return true;
    }
}
