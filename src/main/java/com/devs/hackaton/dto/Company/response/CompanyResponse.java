package com.devs.hackaton.dto.Company.response;

import com.devs.hackaton.enums.Company_User_Status;

public record CompanyResponse(
        String id,
        String cnpj,
        String nome,
        String endereco,
        Company_User_Status status

) {
}
