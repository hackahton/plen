package com.devs.hackaton.dto.Project.response;

import com.devs.hackaton.dto.Company.response.CreateCompanyResponse;
import com.devs.hackaton.dto.UserDTOs.response.CreateUserResponse;
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
        List<UUID> companiesIds,
        List<UUID> usersIds

) {
}
