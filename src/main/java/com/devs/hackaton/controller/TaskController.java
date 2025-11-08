package com.devs.hackaton.controller;

import com.devs.hackaton.dto.Task.request.AtribuicaoRequest;
import com.devs.hackaton.dto.Task.request.MudancaRequest;
import com.devs.hackaton.dto.Task.request.TaskRequest;
import com.devs.hackaton.dto.Task.response.TaskResponse;
import com.devs.hackaton.repository.TaskRepository;
import com.devs.hackaton.repository.UserRepository;
import com.devs.hackaton.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createTask(TaskRequest request) {
        TaskResponse response = taskService.criarTask(request);
        return ResponseEntity.status(HttpStatus.OK).body("Criado" + response);
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editTask(MudancaRequest request) {
        taskService.editarTask(request);
        return ResponseEntity.status(HttpStatus.OK).body("Editado" + taskRepository.findById(request.idTask()));
    }

    @PutMapping("/atribuicao")
    public ResponseEntity<?> atribuicaoTask(AtribuicaoRequest request) {
        taskService.atribuirTask(request);
        return ResponseEntity.status(HttpStatus.OK).body("Atribuido" + userRepository.findById(request.idUser()));
    }
}
