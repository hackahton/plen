package com.devs.hackaton.repository;

import com.devs.hackaton.entity.Task;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.enums.Company_User_Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    User findFirstByStatus(Company_User_Status status);
    User findUserByIdAndStatus(UUID id, Company_User_Status status);

}
