package com.innowise.helpdesk.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ResponseErrorValidationService {

    ResponseEntity<Object> getValidationError(BindingResult result);
}
