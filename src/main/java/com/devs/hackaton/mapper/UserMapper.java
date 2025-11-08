package com.devs.hackaton.mapper;
import com.devs.hackaton.dto.UserDTOs.request.UserRequest;
import com.devs.hackaton.dto.UserDTOs.response.UserResponse;

import com.devs.hackaton.entity.User;


public class UserMapper {

        public static User toEntity(UserRequest request){
            return User.builder()
                    .name(request.name())
                    .email(request.email())
                    .password(request.password())
                    .cpf(request.cpf())
                    .role(request.role())
                    .status(request.status())
                    .projects(request.projects())
                    .tasks(request.tasks())
                    .company(request.company())
                    .tags(request.tags())
                    .build();
        }

        public static UserResponse toResponse(User user){
            return UserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .status(user.getStatus())
                    .projects(user.getProjects())
                    .tasks(user.getTasks())
                    .company(user.getCompany())
                    .tags(user.getTags())
                    .build();
        }
    }

