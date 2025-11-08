package com.devs.hackaton.controller;

import com.devs.hackaton.dto.UserDTOs.request.LoginUserRequest;
import com.devs.hackaton.dto.UserDTOs.request.UpdatePassword;
import com.devs.hackaton.dto.UserDTOs.request.UpdateUserTag;
import com.devs.hackaton.dto.UserDTOs.request.UserRequest;
import com.devs.hackaton.dto.UserDTOs.response.CreateUserResponse;
import com.devs.hackaton.dto.UserDTOs.response.FindUserResponse;
import com.devs.hackaton.dto.UserDTOs.response.LoginUserReponse;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.security.userdetails.UserDetailsImpl;
import com.devs.hackaton.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestBody UserRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User logged = userDetails.getUser();

        try{
            return ResponseEntity.ok(userService.createUser(request, logged));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel criar um usuário");
        }
    }

    @PostMapping("/createADM")
    public ResponseEntity<?> createUserADM(
            @RequestBody UserRequest request) {
        try{
            return ResponseEntity.ok(userService.createUserADM(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A requisição está invalida");
        }
    }

    @GetMapping
    public ResponseEntity<List<CreateUserResponse>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(
            @RequestBody @Valid final LoginUserRequest request
    ) {
        try{
            LoginUserReponse response = userService.loginUsuario(request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível fazer o login");
        }

    }

    @PutMapping("/toggle/{id}")
    @Operation(summary = "Toggle user status")
    public ResponseEntity<?> toggleUserStatus(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User logged = userDetails.getUser();
        userService.toggleUserStatus(id, logged);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/password/{id}")
    @Operation(summary = "Update user password")
    public ResponseEntity<?> updatePassword(
            @PathVariable UUID id,
            @RequestBody UpdatePassword request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User logged = userDetails.getUser();

        try{
            userService.UpdatePassword(id, request, logged);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel atualizar a senha");
        }
    }

    @PutMapping("update/tag/{id}")
    @Operation(summary = "Update user tag")
    public ResponseEntity<?> updateUserTag(
            @PathVariable UUID id,
            @RequestBody UpdateUserTag tag,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User logged = userDetails.getUser();

        try{
            userService.updateUserTag(id, tag, logged);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel atualizar a tag");
        }

    }

    @PutMapping("delete/tag/{id}")
    @Operation(summary = "Delete user tag")
    public ResponseEntity<?> deleteUserTag(
            @PathVariable UUID id,
            @RequestBody UpdateUserTag tag,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User logged = userDetails.getUser();

        try{
            userService.removeUserTag(id, tag, logged);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel deletar a tag");
        }

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<FindUserResponse> getUserById(
            @PathVariable UUID id,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User logged = userDetails.getUser();

        return ResponseEntity.ok(userService.findUserById(id));
    }

}
