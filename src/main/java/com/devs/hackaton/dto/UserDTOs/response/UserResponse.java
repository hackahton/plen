package com.devs.hackaton.dto.UserDTOs.response;


import com.devs.hackaton.dto.Company.response.CompanyResponse;
import com.devs.hackaton.dto.Project.response.ProjectResponse;
import com.devs.hackaton.dto.Tag.response.TagResponse;
import com.devs.hackaton.dto.Task.response.TaskResponse;
import com.devs.hackaton.entity.Company;
import com.devs.hackaton.entity.Project;
import com.devs.hackaton.entity.Tag;
import com.devs.hackaton.entity.Task;
import com.devs.hackaton.enums.Company_User_Status;
import com.devs.hackaton.enums.Role;
import lombok.Builder;
import java.util.List;
import java.util.UUID;
@Builder
public record UserResponse(
        UUID id,
        String name,
        String email,
        Role role,
        Company_User_Status status,
        CompanyResponse company,
        List<ProjectResponse> projects,
        List<TagResponse> tags,
        List<TaskResponse> tasks

) {
}
