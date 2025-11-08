package com.devs.hackaton.dto.Company.response;

import java.util.UUID;

public record CreateCompanyResponse(
        UUID id,
        String cnpj,
        String endereco
) {
}
