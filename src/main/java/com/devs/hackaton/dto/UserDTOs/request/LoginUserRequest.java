package com.devs.hackaton.dto.UserDTOs.request;

import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(
        @NotBlank(message = "Email obrigatório")
        String email,
        @NotBlank(message = "Senha obrigatória")
        String password
) {
}
