package com.devs.hackaton.dto.Company.request;

import com.devs.hackaton.enums.Company_User_Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateCompanyRequest(
        @NotBlank
        String cnpj,

        @NotBlank
        String nome,

        @NotBlank
        String endereco,

        @NotNull
        Company_User_Status status
) {
}
