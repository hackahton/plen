package com.devs.hackaton.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devs.hackaton.entity.Project;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
