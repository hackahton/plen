package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Project.request.CreateProjectRequest;
import com.devs.hackaton.dto.Project.request.UpdateProjectRequest;
import com.devs.hackaton.dto.Project.response.CreateProjectResponse;
import com.devs.hackaton.dto.Project.response.UpdateProjectResponse;
import com.devs.hackaton.entity.Company;
import com.devs.hackaton.entity.Project;
import com.devs.hackaton.entity.Task;
import com.devs.hackaton.entity.User;

public class ProjectMapper {
    public static Project toProjectEntity(CreateProjectRequest projectRequest) {
        return Project.builder().
                name(projectRequest.name()).
                description(projectRequest.description()).
                priority(projectRequest.priority()).
                build();
    }

    public static Project toProjectEntity(UpdateProjectRequest projectRequest) {
        return Project.builder().
                name(projectRequest.name()).
                description(projectRequest.description()).
                priority(projectRequest.priority()).
                projectStatus(projectRequest.projectStatus()).
                build();
    }

    public static UpdateProjectResponse toUpdateProjectResponse(Project project){
        return UpdateProjectResponse.builder().
                id(project.getId()).
                name(project.getName()).
                description(project.getDescription()).
                priority(project.getPriority()).
                projectStatus(project.getProjectStatus()).
                companiesId(project.getCompanies().stream().map(Company::getId).toList()).
                usersId(project.getUsers().stream().map(User::getId).toList()).
                tasksIds(project.getTasks().stream().map(Task::getId).toList()).
                build();
    }

    public static CreateProjectResponse toCreateProjectResponse(Project project){
        return CreateProjectResponse.builder().
                id(project.getId()).
                name(project.getName()).
                description(project.getDescription()).
                priority(project.getPriority()).
                projectStatus(project.getProjectStatus()).
                companiesIds(project.getCompanies().stream().map(Company::getId).toList()).
                usersIds(project.getUsers().stream().map(User::getId).toList()).
                build();
    }
}
