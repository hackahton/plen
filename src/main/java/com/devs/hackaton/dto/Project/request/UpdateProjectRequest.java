package com.devs.hackaton.dto.Project.request;

import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.ProjectStatus;

import java.util.List;
import java.util.UUID;

public record UpdateProjectRequest(
        String name,
        String description,
        Priority priority,
        ProjectStatus projectStatus,
        UUID companyId,
        List<UUID> userId
) {
}
