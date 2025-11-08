package com.devs.hackaton.exception.User;

public class ExistsUserException extends RuntimeException {
    public ExistsUserException() {
       super("O Usuário já cadastrado");
    }
}
