package com.bank.validator;

public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}