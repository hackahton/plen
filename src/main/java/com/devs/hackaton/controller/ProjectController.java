package com.devs.hackaton.controller;


import com.devs.hackaton.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

//    @PostMapping
//    public ResponseEntity<CreateProjectResponse> saveProject(CreateProjectRequest request){
//        return ResponseEntity.ok(projectService.saveProject(request));
//    }
}
