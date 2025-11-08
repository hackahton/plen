package com.devs.hackaton.exception;

public class CompanyAlreadyExistException extends RuntimeException {
    public CompanyAlreadyExistException() {
        super("Empresa já cadastrada.");
    }
}
