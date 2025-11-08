package com.devs.hackaton.dto.Project.response;

import com.devs.hackaton.dto.Task.response.TaskResponse;
import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.ProjectStatus;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ProjectReportResponse(
        UUID id,
        String name,
        String description,
        Priority priority,
        ProjectStatus projectStatus,
        List<TaskResponse> tasks
) {
}