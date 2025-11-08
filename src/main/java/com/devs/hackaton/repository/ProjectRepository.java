package com.devs.hackaton.repository;
import com.devs.hackaton.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import com.devs.hackaton.entity.Project;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Task> findAllByProject(UUID id);
}
