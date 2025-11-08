package com.devs.hackaton.exception;

public class UsuarioNaoEcontradoPeloEmailDoTokenException extends RuntimeException {
    public UsuarioNaoEcontradoPeloEmailDoTokenException() {
        super("Usuário não encontrado pelo email contido no token informado.");
    }
}
