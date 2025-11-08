package com.devs.hackaton.exception.Company;

public class EditCompanyRequestsNullException extends RuntimeException {
    public EditCompanyRequestsNullException() {
        super("Nada alterado, pois o request está nulo.");
    }
}
