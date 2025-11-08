package com.devs.hackaton.controller;

import com.devs.hackaton.dto.Company.request.CreateCompanyRequest;
import com.devs.hackaton.dto.Company.request.EditCompanyRequest;
import com.devs.hackaton.dto.Company.response.CompanyResponse;
import com.devs.hackaton.dto.Company.response.CreateCompanyResponse;
import com.devs.hackaton.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CreateCompanyResponse> createCompany(@RequestBody CreateCompanyRequest request){
        CreateCompanyResponse response = companyService.createCompany(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{idCompany}")
    public ResponseEntity<CompanyResponse> editCompany(@PathVariable UUID idCompany, @RequestBody EditCompanyRequest request){
        CompanyResponse response = companyService.editCompany(idCompany, request);
        return ResponseEntity.ok(response);
    }
}
