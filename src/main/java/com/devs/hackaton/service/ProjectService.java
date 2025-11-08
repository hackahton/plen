package com.devs.hackaton.service;

import com.devs.hackaton.dto.Project.request.CreateProjectRequest;
import com.devs.hackaton.dto.Project.response.CreateProjectResponse;
import com.devs.hackaton.entity.Company;
import com.devs.hackaton.entity.Project;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.enums.Role;
import com.devs.hackaton.mapper.ProjectMapper;
import com.devs.hackaton.repository.CompanyRepository; // Novo Import
import com.devs.hackaton.repository.ProjectRepository;
import com.devs.hackaton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository; // Adicionado

    @Autowired
    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository, CompanyRepository companyRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional
    public CreateProjectResponse createProject(CreateProjectRequest request, Role userRole) {
        if (userRole == Role.COLABORADOR) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Colaboradores não podem criar novos projetos. Somente GESTOR ou SUPERVISOR.");
        }
        if (request.companyId() == null || request.userId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID da Empresa e do Usuário são obrigatórios para criação do projeto.");
        }

        Project project = ProjectMapper.toProjectEntity(request);

        Company company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa (ID: " + request.companyId() + ") não encontrada."));

        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário inicial (ID: " + request.userId() + ") não encontrado."));

        project.getCompanies().add(company);
        company.getProjects().add(project);
        project.getUsers().add(user);
        user.getProjects().add(project);

        project = projectRepository.save(project);

        return ProjectMapper.toResponse(project);
    }

    @Transactional
    public CreateProjectResponse updateProject(UUID projectId, CreateProjectRequest request, Role userRole) {

        Project existingProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto com ID " + projectId + " não encontrado."));

        if (existingProject.getProjectStatus() != request.projectStatus()) {
            if (userRole == Role.COLABORADOR) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                        "Usuário com função " + userRole + " não tem permissão para alterar o status do projeto.");
            }
        }


        existingProject.setName(request.name());
        existingProject.setDescription(request.description());
        existingProject.setPriority(request.priority());
        existingProject.setProjectStatus(request.projectStatus());


        return ProjectMapper.toResponse(projectRepository.save(existingProject));
    }


    @Transactional
    public CreateProjectResponse associateUser(UUID projectId, UUID userId, Role userRole) {
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

        return ProjectMapper.toResponse(projectRepository.save(project));
    }

    public List<CreateProjectResponse> findAllProjects() {
        return projectRepository.findAll().stream()
                .map(ProjectMapper::toResponse)
                .collect(Collectors.toList());
    }

    public Optional<CreateProjectResponse> findProjectById(UUID id) {
        return projectRepository.findById(id)
                .map(ProjectMapper::toResponse);
    }

    @Transactional
    public void deleteProject(UUID id) {
        projectRepository.deleteById(id);
    }

}