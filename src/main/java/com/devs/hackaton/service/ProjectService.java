package com.devs.hackaton.service;

import com.devs.hackaton.entity.Project;
import com.devs.hackaton.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> findProjectById(UUID id) {
        return projectRepository.findById(id);
    }

    public void deleteProject(UUID id) {
        projectRepository.deleteById(id);
    }
}