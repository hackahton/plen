package com.devs.hackaton.dto.Project.request;

import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record UpdateProjectRequest(
        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotBlank
        Priority priority,

        @NotBlank
        ProjectStatus projectStatus,

        @NotBlank
        UUID companyId,
        List<UUID> userId
) {
}
