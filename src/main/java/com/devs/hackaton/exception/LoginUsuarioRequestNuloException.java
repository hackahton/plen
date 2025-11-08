package com.devs.hackaton.exception;

public class LoginUsuarioRequestNuloException extends RuntimeException {
    public LoginUsuarioRequestNuloException() {
        super("Request para login de usuário está nulo.");
    }
}
