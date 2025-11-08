package com.devs.hackaton.service;

import com.devs.hackaton.dto.Project.request.CreateProjectRequest;
import com.devs.hackaton.dto.Project.request.UpdateProjectRequest;
import com.devs.hackaton.dto.Project.response.CreateProjectResponse;
import com.devs.hackaton.dto.Project.response.ProjectReportResponse;
import com.devs.hackaton.dto.Project.response.UpdateProjectResponse;
import com.devs.hackaton.entity.Company;
import com.devs.hackaton.entity.Project;
import com.devs.hackaton.entity.Task;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.enums.Company_User_Status;
import com.devs.hackaton.mapper.ProjectMapper;
import com.devs.hackaton.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final CompanyService companyService;

    public Project findProjectEntityById(UUID id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Projeto não encontrado."));
    }

    public CreateProjectResponse createProject(CreateProjectRequest projectRequest) {
        Project project = ProjectMapper.toProjectEntity(projectRequest);
        projectRepository.save(project);
        return ProjectMapper.toCreateProjectResponse(project);
    }

    public UpdateProjectResponse updateProject(UUID id, UpdateProjectRequest projectRequest) {
        Project project = findProjectEntityById(id);

        if ( projectRequest.name() != null){
            project.setName(projectRequest.name());
        }

        if ( projectRequest.description() != null){
            project.setDescription(projectRequest.description());
        }

        if ( projectRequest.priority() != null){
            project.setPriority(projectRequest.priority());
        }

        if (projectRequest.companyId() != null){
            Company company = companyService.findCompanyEntityById(projectRequest.companyId());
            project.getCompanies().add(company);
        }

        if (projectRequest.userId() != null) {
            for (UUID userId : projectRequest.userId()) {
                User user = userService.findUserEntityByStatusAndId(userId, Company_User_Status.ACTIVE);
                project.getUsers().add(user);
            }
        }

        projectRepository.save(project);

        return ProjectMapper.toUpdateProjectResponse(project);
    }

    public List<ProjectReportResponse> findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return ProjectMapper.toList(projects);
    }

//    public List<ProjectReportResponse> TasksOfProject(UUID id){
//        return projectRepository.findAllByProject(id).stream()
//                .map(n -> new ProjectReportResponse(n.getTitle(),
//                        n.getDescription(),
//                        n.getStatus()))
//                .toList();
//    }

}