package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Company.request.CreateCompanyRequest;
import com.devs.hackaton.entity.Company;

public class CompanyMapper {
    public static Company toEntity(CreateCompanyRequest request) {
        return Company.builder()
                .name(request.name())
                .cnpj(request.cnpj())
                .address(request.address())
                .status(request.status())
                .build();
    }
}
