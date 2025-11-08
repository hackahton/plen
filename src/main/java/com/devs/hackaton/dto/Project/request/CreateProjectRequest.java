package com.devs.hackaton.dto.Project.request;

import com.devs.hackaton.entity.Company;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.ProjectStatus;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record CreateProjectRequest(
        String name,
        String description,
        Priority priority,
        ProjectStatus projectStatus,
        UUID companyId,
        UUID userId

) {
}
