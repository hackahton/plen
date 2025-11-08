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
    public ResponseEntity<?> createCompany(@RequestBody CreateCompanyRequest request){
        try{
            CreateCompanyResponse response = companyService.createCompany(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é possível atender a sua requisição");
        }

    }

    @PutMapping("/{idCompany}")
    public ResponseEntity<?> editCompany(@PathVariable UUID idCompany, @RequestBody EditCompanyRequest request){
        try{
            CompanyResponse response = companyService.editCompany(idCompany, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível editar essa empresa");
        }
    }
}
