package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.UserDTOs.request.UserRequest;
import com.devs.hackaton.dto.UserDTOs.response.CreateUserResponse;
import com.devs.hackaton.dto.UserDTOs.response.FindUserResponse;
import com.devs.hackaton.entity.User;

public class UserMapper {

    public static User toEntity(UserRequest request) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .role(request.role())
                .status(request.status())
                .build();
    }

    public static CreateUserResponse toResponse(User user) {
        return CreateUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .status(user.getStatus())
                .companyId(user.getCompany().getId())
                .build();
    }

    public static FindUserResponse toFindResponse(User user) {
        return FindUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .status(user.getStatus())
                .companyId(user.getCompany().getId())
                .build();
    }

}
