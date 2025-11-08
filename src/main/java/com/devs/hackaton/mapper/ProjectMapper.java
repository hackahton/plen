package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Project.request.CreateProjectRequest;
import com.devs.hackaton.dto.Project.response.CreateProjectResponse;
import com.devs.hackaton.dto.Project.response.ProjectResponse;
import com.devs.hackaton.entity.Project;

import java.util.List;

public class ProjectMapper {
    public static Project toProjectEntity(CreateProjectRequest request) {
        return Project.builder()
                .name(request.name())
                .description(request.description())
                .priority(request.priority())
                .projectStatus(request.projectStatus())
                .companies(request.companies())
                .users(request.users())
                .build();
    }
    public static ProjectResponse toResponse(Project project){
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .priority(project.getPriority())
                .projectStatus(project.getProjectStatus())
                .companies(CompanyMapper.toCompanyResponseList(project.getCompanies()))
                .users(UserMapper.toUserResponseList(project.getUsers()))
                .build();
    }

    public static List<ProjectResponse> toProjectResponseList(List<Project> projects){
        return projects.stream()
                .map(ProjectMapper::toResponse)
                .toList();
    }

}
