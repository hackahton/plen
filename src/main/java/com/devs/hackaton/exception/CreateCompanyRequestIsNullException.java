package com.devs.hackaton.exception;

public class CreateCompanyRequestIsNullException extends RuntimeException {
    public CreateCompanyRequestIsNullException() {
        super("Request para cadastrar empresa está nulo.");
    }
}
