package com.devs.hackaton.dto.Project.request;

import com.devs.hackaton.dto.Company.response.CompanyResponse;
import com.devs.hackaton.dto.UserDTOs.response.CreateUserResponse;
import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.ProjectStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateProjectRequest(
        String name,
        String description,
        Priority priority
) {
}
