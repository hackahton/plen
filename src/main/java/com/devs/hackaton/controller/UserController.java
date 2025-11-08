package com.devs.hackaton.controller;

import com.devs.hackaton.dto.UserDTOs.request.LoginUserRequest;
import com.devs.hackaton.dto.UserDTOs.request.UserRequest;
import com.devs.hackaton.dto.UserDTOs.response.CreateUserResponse;
import com.devs.hackaton.dto.UserDTOs.response.LoginUserReponse;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.security.userdetails.UserDetailsImpl;
import com.devs.hackaton.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(
            @RequestBody UserRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User logged = userDetails.getUser();
        return ResponseEntity.ok(userService.createUser(request, logged));
    }

    @PostMapping("/createADM")
    public ResponseEntity<CreateUserResponse> createUserADM(
            @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.createUserADM(request));
    }

    @GetMapping
    public ResponseEntity<List<CreateUserResponse>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserReponse> loginUsuario(
            @RequestBody @Valid final LoginUserRequest request
    ) {
        LoginUserReponse response = userService.loginUsuario(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
