package com.devs.hackaton.exception;

import com.devs.hackaton.exception.Company.CompanyAlreadyExistException;
import com.devs.hackaton.exception.Company.CompanyNotFoundException;
import com.devs.hackaton.exception.Company.CreateCompanyRequestIsNullException;
import com.devs.hackaton.exception.Company.EditCompanyRequestsNullException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandlerException {

    // Tratamento para validações (@Valid, @NotBlank, etc)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        String message = "Dados inválidos - " + errors;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    //Utilizado no CompanyService quando request para criar empresa estiver nulo
    @ExceptionHandler(CreateCompanyRequestIsNullException.class)
    public ResponseEntity<String> handleCreateCompanyRequestIsNullException(CreateCompanyRequestIsNullException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    //Utilizado no CompanyService quando ja existe empresa com o mesmo cnpj cadastrada
    @ExceptionHandler(CompanyAlreadyExistException.class)
    public ResponseEntity<String> handleCompanyAlreadyExistException(CompanyAlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    //Utilizado no CompanyService quando empresa não encontrada
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<String> handleCompanyNotFoundException(CompanyNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    //Utilizado no CompanyService quando o request para editar empresa for nulo
    @ExceptionHandler(EditCompanyRequestsNullException.class)
    public ResponseEntity<String> handleEditCompanyRequestsNullException(EditCompanyRequestsNullException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
