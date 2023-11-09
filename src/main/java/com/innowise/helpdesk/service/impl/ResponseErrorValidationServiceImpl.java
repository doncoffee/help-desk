package com.innowise.helpdesk.service.impl;

import com.innowise.helpdesk.service.ResponseErrorValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ResponseErrorValidationServiceImpl implements ResponseErrorValidationService {

    @Override
    public ResponseEntity<Object> getValidationError(BindingResult result) {
        String message = null;
        if (result.hasErrors()) {
            message = result.getFieldErrors().isEmpty() ? null : result.getFieldErrors().get(0).getDefaultMessage();
        }
        return message == null ? null : new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
