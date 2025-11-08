package com.devs.hackaton.exception.Company;

public class CompanyAlreadyExistException extends RuntimeException {
    public CompanyAlreadyExistException() {
        super("Empresa já cadastrada.");
    }
}
