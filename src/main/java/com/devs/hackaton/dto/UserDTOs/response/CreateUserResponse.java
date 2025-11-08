package com.devs.hackaton.dto.UserDTOs.response;


import com.devs.hackaton.dto.Company.response.CompanyResponse;
import com.devs.hackaton.enums.Company_User_Status;
import com.devs.hackaton.enums.Role;
import lombok.Builder;

import java.util.UUID;
@Builder
public record CreateUserResponse(
        UUID id,
        String name,
        String email,
        Role role,
        Company_User_Status status,
        UUID companyId
) {
}
