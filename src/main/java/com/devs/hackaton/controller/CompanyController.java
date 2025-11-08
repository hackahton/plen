package com.devs.hackaton.controller;

import com.devs.hackaton.dto.Company.request.CreateCompanyRequest;
import com.devs.hackaton.dto.Company.response.CreateCompanyResponse;
import com.devs.hackaton.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    public ResponseEntity<CreateCompanyResponse> createCompany(CreateCompanyRequest request){
        CreateCompanyResponse response = companyService.createCompany(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
