package com.devs.hackaton.dto.UserDTOs;


import com.devs.hackaton.entity.Company;
import com.devs.hackaton.entity.Project;
import com.devs.hackaton.entity.Tag;
import com.devs.hackaton.entity.Task;
import com.devs.hackaton.enums.Company_User_Status;
import com.devs.hackaton.enums.Role;

import java.util.List;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        String password,
        String cpf,
        Role role,
        Company_User_Status status,
        List<Project> projects,
        List<Task> tasks,
        Company company,
        List<Tag> tags
) {
}
