package com.devs.hackaton.dto.Company.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCompanyRequest(
        @NotBlank
        String cnpj,

        @NotBlank
        String nome,

        @NotBlank
        String endereco
) {
}
