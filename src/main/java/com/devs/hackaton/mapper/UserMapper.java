package com.devs.hackaton.mapper;
import com.devs.hackaton.dto.UserDTOs.request.UserRequest;
import com.devs.hackaton.dto.UserDTOs.response.UserResponse;

import com.devs.hackaton.entity.User;

import java.util.List;


public class UserMapper {

        public static User toEntity(UserRequest request){
            return User.builder()
                    .name(request.name())
                    .email(request.email())
                    .password(request.password())
                    .cpf(request.cpf())
                    .role(request.role())
                    .status(request.status())
                    .company(CompanyMapper.toCompanyEntity(request.company()))
                    .build();
        }

        public static List<UserResponse> toUserResponseList(List<User> users){
            return users.stream()
                    .map(UserMapper::toResponse)
                    .toList();
        }

        public static UserResponse toResponse(User user){
            return UserResponse.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .status(user.getStatus())
                    .projects(ProjectMapper.toProjectResponseList(user.getProjects()))
                    .tasks(TaskMapper.toTaskResponseList(user.getTasks()))
                    .company(CompanyMapper.toCompanyResponse(user.getCompany()))
                    .tags(TagMapper.toTagResponseList(user.getTags()))
                    .build();
        }
    }

