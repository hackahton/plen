package com.devs.hackaton.repository;
import com.devs.hackaton.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    Optional<Company> findByCnpj (String Cnpj);
}
