package com.devs.hackaton.exception;

public class TokenNuloException extends RuntimeException {
    public TokenNuloException() {
        super("Token está nulo.");
    }
}
