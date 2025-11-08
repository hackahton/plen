package com.devs.hackaton.service;

import com.devs.hackaton.entity.Project;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.repository.ProjectRepository;
import com.devs.hackaton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(UUID id) {
        return userRepository.findById(id);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }


}
