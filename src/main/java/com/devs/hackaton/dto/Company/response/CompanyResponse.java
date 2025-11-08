package com.devs.hackaton.dto.Company.response;

import com.devs.hackaton.enums.Company_User_Status;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CompanyResponse(
        UUID id,
        String cnpj,
        String nome,
        String endereco,
        Company_User_Status status
) {
}
