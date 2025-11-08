package com.devs.hackaton.controller;

import com.devs.hackaton.dto.Task.request.AtribuicaoRequest;
import com.devs.hackaton.dto.Task.request.MudancaRequest;
import com.devs.hackaton.dto.Task.request.TaskRequest;
import com.devs.hackaton.dto.Task.response.TaskResponse;
import com.devs.hackaton.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> createTask(TaskRequest request) {
        taskService.criarTask(request);
        return ResponseEntity.status(HttpStatus.OK).body("Criado");
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editTask(MudancaRequest request) {
        taskService.editarTask(request);
        return ResponseEntity.status(HttpStatus.OK).body("Editado");
    }

    @PutMapping("/atribuicao")
    public ResponseEntity<?> atribuicaoTask(AtribuicaoRequest request) {
        taskService.atribuirTask(request);
        return ResponseEntity.status(HttpStatus.OK).body("Atribuido");
    }
}
