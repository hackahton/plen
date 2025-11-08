package com.devs.hackaton.dto.Company.request;

import com.devs.hackaton.enums.Company_User_Status;

public record EditCompanyRequest(
        String nome,
        String endereco,
        Company_User_Status status
) {
}
