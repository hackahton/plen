package com.devs.hackaton.controller;

import com.devs.hackaton.dto.Task.request.AtribuicaoRequest;
import com.devs.hackaton.dto.Task.request.MudancaRequest;
import com.devs.hackaton.dto.Task.request.TaskRequest;
import com.devs.hackaton.dto.Task.response.TaskResponse;
import com.devs.hackaton.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?>createTask(@RequestBody TaskRequest request) {
        try {
            TaskResponse response = taskService.criarTask(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel criar uma task");
        }

    }

    @PutMapping("/editar")
    public ResponseEntity<?> editTask(@RequestBody MudancaRequest request) {
        try{
            taskService.editarTask(request);
            return ResponseEntity.status(HttpStatus.OK).body("Editado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel editar a task");
        }
    }

    @PutMapping("/atribuicao")
    public ResponseEntity<?> atribuicaoTask(@RequestBody AtribuicaoRequest request) {
        try{
            taskService.atribuirTask(request);
            return ResponseEntity.status(HttpStatus.OK).body("Atribuido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel atribuir uma task");
        }
    }
}
