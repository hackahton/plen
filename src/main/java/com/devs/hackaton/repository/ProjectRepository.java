package com.devs.hackaton.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devs.hackaton.entity.Project;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
