package com.devs.hackaton.dto.request;

import com.devs.hackaton.enums.Company_User_Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCompanyRequest(
        @NotBlank
        String nome,

        @NotBlank
        String endereco,

        @NotNull
        Company_User_Status status
) {
}
