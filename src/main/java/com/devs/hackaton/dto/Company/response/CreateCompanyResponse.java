package com.devs.hackaton.dto.Company.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateCompanyResponse(
        String id,
        String cnpj,
        String endereco
) {
}
