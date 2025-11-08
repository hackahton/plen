package com.devs.hackaton.repository;

import com.devs.hackaton.entity.User;
import com.devs.hackaton.enums.Company_User_Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<Object> findByCpf(String cpf);
    Optional<Object> findByEmail(String email);
    User findFirstByStatus(Company_User_Status status);
}
