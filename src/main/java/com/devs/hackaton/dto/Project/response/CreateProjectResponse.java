package com.devs.hackaton.dto.Project.response;

import com.devs.hackaton.dto.Company.response.CreateCompanyResponse;
import com.devs.hackaton.dto.UserDTOs.response.UserResponse;
import com.devs.hackaton.entity.Company;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.ProjectStatus;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record CreateProjectResponse(
        UUID id,
        String name,
        String description,
        Priority priority,
        ProjectStatus projectStatus,
        List<CreateCompanyResponse> companies,
        List<UserResponse> users

) {
}
