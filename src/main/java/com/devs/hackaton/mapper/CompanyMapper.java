package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Company.request.CreateCompanyRequest;
import com.devs.hackaton.dto.Company.response.CreateCompanyResponse;
import com.devs.hackaton.entity.Company;

public class CompanyMapper {
    public Company toCompanyEntity(CreateCompanyRequest request){
        return Company.builder()
                .cnpj(request.cnpj())
                .nome(request.nome())
                .endereco(request.endereco())
                .build();
    }

    public CreateCompanyResponse toCreateCompanyResponse(Company company){
        return new CreateCompanyResponse(
                company.getId(),
                company.getCnpj(),
                company.getEndereco()
        );
    }
}
