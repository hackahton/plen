package com.devs.hackaton.dto.Company.request;

import com.devs.hackaton.enums.Company_User_Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCompanyRequest(
        @NotBlank(message = "CNPJ Obrigatório")
        String cnpj,

        @NotBlank(message = "O nome não pode ser nulo ou branco")
        String name,

        @NotBlank(message = "O Endereço não pode estar vazio ou nulo")
        String address,

        @NotNull(message = "O status não pode ser nulo")
        Company_User_Status status
) {
}
