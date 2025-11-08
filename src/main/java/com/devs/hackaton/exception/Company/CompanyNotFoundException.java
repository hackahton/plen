package com.devs.hackaton.exception.Company;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Empresa não encontrada.");
    }
}
