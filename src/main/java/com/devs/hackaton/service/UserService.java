package com.devs.hackaton.service;

import com.devs.hackaton.dto.UserDTOs.request.UpdatePassword;
import com.devs.hackaton.dto.UserDTOs.request.UpdateUserTag;
import com.devs.hackaton.dto.UserDTOs.request.UserRequest;
import com.devs.hackaton.dto.UserDTOs.response.CreateUserResponse;
import com.devs.hackaton.dto.UserDTOs.response.FindUserResponse;
import com.devs.hackaton.entity.Company;
import com.devs.hackaton.entity.Tag;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.enums.Company_User_Status;
import com.devs.hackaton.exception.User.ExistsUserException;
import com.devs.hackaton.exception.User.UserNotFoundException;
import com.devs.hackaton.mapper.UserMapper;
import com.devs.hackaton.repository.CompanyRepository;
import com.devs.hackaton.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final TagService tagService;

    public UserService(UserRepository userRepository, CompanyRepository companyRepository, TagService tagService) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.tagService = tagService;
    }

    public CreateUserResponse createUser(UserRequest request) {
        User user = UserMapper.toEntity(request);
        Company company = companyRepository.findById(request.companyId()).orElseThrow(RuntimeException::new);
        user.setCompany(company);
        user.setStatus(Company_User_Status.ACTIVE);

        userRepository.save(user);

        return UserMapper.toResponse(user);
    }

    public List<CreateUserResponse> findAllUsers() {
        log.info("Fetching all users");
        return userRepository.findAll().stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    public FindUserResponse findUserById(UUID id) {
        log.info("Fetching user with id: {}", id);
        User user = findUserEntityById(id);
        return UserMapper.toFindResponse(user);
    }

    public User findUserEntityById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }


    public void toggleUserStatus(UUID id) {
        User user = findUserEntityById(id);
        user.setStatus(toggleStatus(user.getStatus()));

        userRepository.save(user);
    }

    private Company_User_Status toggleStatus(Company_User_Status status) {
        return status.equals(Company_User_Status.ACTIVE)
                ? Company_User_Status.INACTIVE
                : Company_User_Status.ACTIVE;
    }

    public void UpdatePassword(UUID id, UpdatePassword request) {
        User user = findUserEntityById(id);
        user.setPassword(request.password());
        userRepository.save(user);
    }

    public void updateUserTag(UUID id, UpdateUserTag tag){
        User user = findUserEntityById(id);
        Tag tagEntity = tagService.findTagEntityById(tag.tagId());

        user.getTags().add(tagEntity);
        userRepository.save(user);
    }

    public void removeUserTag(UUID id, UpdateUserTag tag){
        User user = findUserEntityById(id);
        Tag tagEntity = tagService.findTagEntityById(tag.tagId());

        user.getTags().remove(tagEntity);
        userRepository.save(user);
    }


    public User findUserEntityByStatusAndId(UUID id, Company_User_Status status){
        return userRepository.findUserByIdAndStatus(id, status);
    }
}
