package com.devs.hackaton.exception.User;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("Usuário não tem permissão para executar esta ação.");
    }
}
