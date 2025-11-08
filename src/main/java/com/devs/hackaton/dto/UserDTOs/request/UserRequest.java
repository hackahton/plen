package com.devs.hackaton.dto.UserDTOs.request;


import com.devs.hackaton.dto.Company.request.CreateCompanyRequest;
import com.devs.hackaton.entity.Company;
import com.devs.hackaton.entity.Project;
import com.devs.hackaton.entity.Tag;
import com.devs.hackaton.entity.Task;
import com.devs.hackaton.enums.Company_User_Status;
import com.devs.hackaton.enums.Role;
import lombok.Builder;
import java.util.List;

@Builder
public record UserRequest(
        String name,
        String email,
        String password,
        String cpf,
        Role role,
        Company_User_Status status,
        CreateCompanyRequest company

) {


}
