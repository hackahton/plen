package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Project.request.CreateProjectRequest;
import com.devs.hackaton.dto.Project.response.CreateProjectResponse;
import com.devs.hackaton.entity.Project;

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
    public static CreateProjectResponse toResponse(Project project){
        return CreateProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .priority(project.getPriority())
                .projectStatus(project.getProjectStatus())
                .companies(project.getCompanies())
                .users(project.getUsers())
                .build();
    }

}
