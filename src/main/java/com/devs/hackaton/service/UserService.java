package com.devs.hackaton.service;

import com.devs.hackaton.dto.UserDTOs.request.UserRequest;
import com.devs.hackaton.dto.UserDTOs.response.UserResponse;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.exception.User.ExistsUserException;
import com.devs.hackaton.mapper.UserMapper;
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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest request) {
        User user = userRepository.save(UserMapper.toEntity(request));
        return UserMapper.toResponse(user);
    }

    public List<UserResponse> findAllUsers() {
        log.info("Fetching all users");

        if(userRepository.findAll().isEmpty()){
            log.warn("No users found in the database");
            throw new IllegalArgumentException("No users found in the database");
        }
        return userRepository.findAll().stream()
                .map(UserMapper::toResponse)
                .toList();
    }



    public User findUserById(UUID id) {
        log.info("Fetching user with id: {}", id);

        return userRepository.findById(id)
                .orElseThrow(ExistsUserException::new);
    }



    public void toggleUserStatus(UUID id) {
        log.info("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }


}
