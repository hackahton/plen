package com.devs.hackaton.dto.Company.request;

import com.devs.hackaton.enums.Company_User_Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditCompanyRequest(
        @NotBlank(message = "O nome da empresa não pode ser branco ou nulo")
        String nome,

        @NotBlank(message = "O endereço não pode ser em branco ou nulo")
        String endereco,

        @NotNull(message = "O status não pode ser nulo")
        Company_User_Status status
) {
}
