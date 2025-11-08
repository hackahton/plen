package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Project.request.CreateProjectRequest;
import com.devs.hackaton.dto.Project.response.CreateProjectResponse;
import com.devs.hackaton.entity.Project;

import java.util.Collections;
import java.util.stream.Collectors;

public class ProjectMapper {

    public static Project toProjectEntity(CreateProjectRequest request) {
        return Project.builder()
                .name(request.name())
                .description(request.description())
                .priority(request.priority())
                .projectStatus(request.projectStatus())
                .companies(Collections.emptyList())
                .users(Collections.emptyList())
                .build();
    }

    public static CreateProjectResponse toResponse(Project project){
        return CreateProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .priority(project.getPriority())
                .projectStatus(project.getProjectStatus())
                .companies(project.getCompanies() != null ? project.getCompanies().stream()
                        .map(CompanyMapper::toCreateCompanyResponse)
                        .collect(Collectors.toList()) : Collections.emptyList())
                .users(project.getUsers() != null ? project.getUsers().stream()
                        .map(UserMapper::toResponse)
                        .collect(Collectors.toList()) : Collections.emptyList())
                .build();
    }

}