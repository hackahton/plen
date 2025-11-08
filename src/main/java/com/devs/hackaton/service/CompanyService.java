package com.devs.hackaton.service;

import com.devs.hackaton.dto.Company.request.CreateCompanyRequest;
import com.devs.hackaton.dto.Company.response.CreateCompanyResponse;
import com.devs.hackaton.entity.Company;
import com.devs.hackaton.exception.CreateCompanyRequestIsNullException;
import com.devs.hackaton.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.devs.hackaton.mapper.CompanyMapper;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CreateCompanyResponse createCompany (CreateCompanyRequest request){
        log.info("Verificando se o request para cadastrar a empresa...");
        if(Objects.isNull(request)){
            log.error("Request para cadastrar empresa está nulo.");
            throw new CreateCompanyRequestIsNullException();
        }

        log.info("Request verificado e não está nulo.");
        log.info("Criando uma nova empresa.");
        Company newCompany = CompanyMapper.toCompanyEntity(request);
        log.info("Empresa criada com sucesso.");
        log.info("Salvando empresa no BD...");
        companyRepository.save(newCompany);
        log.info("Empresa salvo com sucesso. ID: {}",newCompany.getId());
        return new CreateCompanyResponse(newCompany.getId(),newCompany.getCnpj(),newCompany.getEndereco());
    }


}
