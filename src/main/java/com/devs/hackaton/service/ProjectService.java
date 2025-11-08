package com.devs.hackaton.service;

import com.devs.hackaton.dto.Project.request.CreateProjectRequest;
import com.devs.hackaton.entity.Project;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.enums.Role;
import com.devs.hackaton.repository.ProjectRepository;
import com.devs.hackaton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository; // Adicionado para validar associações

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public Project saveProject(Project project, Role userRole) {

        if (project.getId() == null) {
            if (userRole == Role.COLABORADOR) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                        "Colaboradores não podem criar novos projetos. Somente GESTOR ou SUPERVISOR.");
            }
            return projectRepository.save(project);
        }


        Optional<Project> existingProjectOpt = projectRepository.findById(project.getId());

        if (existingProjectOpt.isPresent()) {
            Project existingProject = existingProjectOpt.get();

            if (existingProject.getProjectStatus() != project.getProjectStatus()) {


                if (userRole == Role.COLABORADOR) {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                            "Usuário com função " + userRole + " não tem permissão para alterar o status do projeto.");
                }
            }

            return projectRepository.save(project);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Projeto com ID " + project.getId() + " não encontrado.");
        }
    }

    public Project associateUser(UUID projectId, UUID userId, Role userRole) {
        if (userRole == Role.COLABORADOR) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Somente GESTOR ou SUPERVISOR podem associar usuários a um projeto.");
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado."));

        if (project.getUsers().contains(user)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já está associado a este projeto.");
        }

        project.getUsers().add(user);
        user.getProjects().add(project);

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