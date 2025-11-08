package com.devs.hackaton.controller;


import com.devs.hackaton.dto.Project.request.CreateProjectRequest;
import com.devs.hackaton.dto.Project.response.CreateProjectResponse;
import com.devs.hackaton.dto.UserDTOs.request.UserRequest;
import com.devs.hackaton.dto.UserDTOs.response.UserResponse;
import com.devs.hackaton.service.ProjectService;
import com.devs.hackaton.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<CreateProjectResponse> saveProject(CreateProjectRequest request){
        return ResponseEntity.ok(projectService.saveProject(request));
    }
}
