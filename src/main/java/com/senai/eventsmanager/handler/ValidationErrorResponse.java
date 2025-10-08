package com.senai.eventsmanager.handler;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ValidationErrorResponse {

    int status;
    String message;
    private Map<String, String> errors;
    
}