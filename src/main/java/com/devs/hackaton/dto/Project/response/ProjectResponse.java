package com.devs.hackaton.dto.Project.response;

import com.devs.hackaton.dto.Company.response.CompanyResponse;
import com.devs.hackaton.dto.Tag.response.TagResponse;
import com.devs.hackaton.dto.Task.response.TaskResponse;
import com.devs.hackaton.dto.UserDTOs.response.UserResponse;
import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.ProjectStatus;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ProjectResponse(
        UUID id,
        String name,
        String description,
        Priority priority,
        ProjectStatus projectStatus,
        List<CompanyResponse> companies,
        List<UserResponse> users,
        List<TagResponse> tags,
        List<TaskResponse> tasks
) {
}
