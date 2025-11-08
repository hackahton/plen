package com.devs.hackaton.controller;

import com.devs.hackaton.dto.Project.request.CreateProjectRequest;
import com.devs.hackaton.dto.Project.response.CreateProjectResponse;
import com.devs.hackaton.dto.Project.response.ProjectReportResponse;
import com.devs.hackaton.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> saveProject(@RequestBody CreateProjectRequest request){
        try {
            return ResponseEntity.ok(projectService.createProject(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel criar um projeto");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectReportResponse>> findAllProjects(){
        return ResponseEntity.ok(projectService.findAllProjects());
    }
}
