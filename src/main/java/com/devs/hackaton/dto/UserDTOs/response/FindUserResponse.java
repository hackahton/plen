package com.devs.hackaton.dto.UserDTOs.response;

import com.devs.hackaton.dto.Project.response.ProjectResponse;
import com.devs.hackaton.dto.Tag.response.TagResponse;
import com.devs.hackaton.dto.Task.response.TaskResponse;
import com.devs.hackaton.enums.Company_User_Status;
import com.devs.hackaton.enums.Role;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record FindUserResponse (
        UUID id,
        String name,
        String email,
        Role role,
        Company_User_Status status,
        UUID companyId,
        List<ProjectResponse> projects,
        List<TagResponse> tags,
        List<TaskResponse> tasks
) {
}
