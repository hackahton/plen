package com.devs.hackaton.service;

import com.devs.hackaton.dto.Company.request.CreateCompanyRequest;
import com.devs.hackaton.dto.Company.request.EditCompanyRequest;
import com.devs.hackaton.dto.Company.response.CompanyResponse;
import com.devs.hackaton.dto.Company.response.CreateCompanyResponse;
import com.devs.hackaton.entity.Company;
import com.devs.hackaton.exception.Company.CompanyAlreadyExistException;
import com.devs.hackaton.exception.Company.CompanyNotFoundException;
import com.devs.hackaton.exception.Company.EditCompanyRequestsNullException;
import com.devs.hackaton.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.devs.hackaton.mapper.CompanyMapper;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CreateCompanyResponse createCompany (CreateCompanyRequest request){
        log.info("Verificando se o request para cadastrar a empresa...");
        Company newCompany = companyRepository.findByCnpj(request.cnpj()).orElse(null);
        if(newCompany != null){
            throw new CompanyAlreadyExistException();
        }
        log.info("Criando uma nova empresa.");
        newCompany = CompanyMapper.toCompanyEntity(request);
        log.info("Empresa criada com sucesso.");
        log.info("Salvando empresa no BD...");
        companyRepository.save(newCompany);
        log.info("Empresa salvo com sucesso. ID: {}",newCompany.getId());
        return new CreateCompanyResponse(newCompany.getId(),newCompany.getCnpj(),newCompany.getEndereco());
    }

    public CompanyResponse editCompany(UUID idCompany, EditCompanyRequest request){
        Company company = companyRepository.findById(idCompany).orElseThrow(CompanyNotFoundException::new);

        if(Objects.isNull(request)){
            throw new EditCompanyRequestsNullException();
        }

        if (request.nome().trim() != null){
            company.setNome(request.nome());
        }
        if(request.endereco().trim() != null){
            company.setEndereco(request.endereco());
        }
        if(request.status() != null){
            company.setStatus(request.status());
        }

        companyRepository.save(company);

        return new CompanyResponse(company.getId(),company.getCnpj(), company.getNome(),company.getEndereco(),company.getStatus());
    }

    public Company findCompanyEntityById(UUID id){
        return companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }


}
