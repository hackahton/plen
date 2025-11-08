package com.devs.hackaton.controller;


import com.devs.hackaton.dto.Project.request.CreateProjectRequest;
import com.devs.hackaton.dto.Project.response.CreateProjectResponse;
import com.devs.hackaton.enums.Role;
import com.devs.hackaton.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<CreateProjectResponse> createProject(@RequestBody CreateProjectRequest request){ // Adicionado @RequestBody

        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request, Role.GESTOR));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateProjectResponse> updateProject(@PathVariable UUID id, @RequestBody CreateProjectRequest request){
        return ResponseEntity.ok(projectService.updateProject(id, request, Role.GESTOR));
    }

    @GetMapping
    public ResponseEntity<List<CreateProjectResponse>> findAllProjects(){
        return ResponseEntity.ok(projectService.findAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateProjectResponse> findProjectById(@PathVariable UUID id){
        return projectService.findProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{projectId}/associate-user/{userId}")
    public ResponseEntity<CreateProjectResponse> associateUser(@PathVariable UUID projectId, @PathVariable UUID userId){
        // NOTE: Role.GESTOR usado como placeholder
        return ResponseEntity.ok(projectService.associateUser(projectId, userId, Role.GESTOR));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable UUID id){
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}