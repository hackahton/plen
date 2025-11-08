package com.devs.hackaton.dto.Project.request;

import com.devs.hackaton.dto.Company.response.CompanyResponse;
import com.devs.hackaton.dto.UserDTOs.response.CreateUserResponse;
import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateProjectRequest(
        @NotBlank(message = "O nome não pode ser nulo ou branco")
        String name,

        @NotBlank(message = "O nome não pode ser nulo ou branco")
        String description,

        @NotNull(message = "O nome não pode ser nulo ou branco")
        Priority priority
) {
}
