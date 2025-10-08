package com.senai.eventsmanager.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TemSalmoValidador implements ConstraintValidator <DeveTerSalmo, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        String lowerValue = value.toLowerCase();
            return lowerValue.matches(".*\\bporque\\b.*") && lowerValue.matches(".*\\brosto\\b.*");
        }
    }


