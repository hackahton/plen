package com.devs.hackaton.controller;

import com.devs.hackaton.dto.Task.request.TaskRequest;
import com.devs.hackaton.dto.Task.response.TaskResponse;
import com.devs.hackaton.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(TaskRequest request) {
        return ResponseEntity.ok(taskService.criarTask(request));
    }

    @PutMapping("/editar")
    public ResponseEntity<TaskResponse> editTask(TaskRequest request) {
        return ResponseEntity.ok(taskService.editarTask(request));
    }

    @PutMapping("/atribuicao")
    public ResponseEntity<TaskResponse> atribuicaoTask(TaskRequest request) {
        return ResponseEntity.ok(taskService.atribuirTask(request);
    }
}
