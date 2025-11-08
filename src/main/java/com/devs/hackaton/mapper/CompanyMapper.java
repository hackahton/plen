package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Company.request.CreateCompanyRequest;
import com.devs.hackaton.dto.Company.response.CompanyResponse;
import com.devs.hackaton.dto.Company.response.CreateCompanyResponse;
import com.devs.hackaton.entity.Company;
import com.devs.hackaton.enums.Company_User_Status;

import java.util.List;

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

    public static CompanyResponse toCompanyResponse(Company company){
        return CompanyResponse.builder()
                .id(company.getId())
                .cnpj(company.getCnpj())
                .nome(company.getNome())
                .endereco(company.getEndereco())
                .status(company.getStatus())
                .build();
    }


    public static List<CompanyResponse> toCompanyResponseList(List<Company> companies){
        return companies.stream()
                .map(CompanyMapper::toCompanyResponse)
                .toList();
    }
}
