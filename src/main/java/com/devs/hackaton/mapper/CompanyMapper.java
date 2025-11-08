package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Company.request.CreateCompanyRequest;
import com.devs.hackaton.dto.Company.response.CreateCompanyResponse;
import com.devs.hackaton.entity.Company;
import com.devs.hackaton.enums.Company_User_Status;

public class CompanyMapper {
    public static Company toCompanyEntity(CreateCompanyRequest request){
        return Company.builder()
                .cnpj(request.cnpj())
                .nome(request.nome())
                .endereco(request.endereco())
                .status(Company_User_Status.ACTIVE)
                .build();
    }

    public static CreateCompanyResponse toCreateCompanyResponse(Company company){
        return new CreateCompanyResponse(
                company.getId(),
                company.getCnpj(),
                company.getEndereco()
        );
    }
}
