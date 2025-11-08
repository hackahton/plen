package com.devs.hackaton.service;

import com.devs.hackaton.dto.request.TaskRequest;
import com.devs.hackaton.entity.Task;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.enums.Role;
import com.devs.hackaton.enums.TaskStatus;
import com.devs.hackaton.repository.TaskRepository;
import com.devs.hackaton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public void criarTask(TaskRequest request){
        User user = userRepository.findById(request.user_id())
                .orElseThrow(() -> new IllegalArgumentException("Nao encontrado"));

        if (!user.getRole().equals(Role.GESTOR)){
            throw new IllegalArgumentException("Voce nao tem acesso a isso");
        }

        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setTerm(request.term());
        task.setDifficulty(request.difficulty());
        task.setStatus(TaskStatus.PENDENTE);
        task.setPriority(request.priority());
        taskRepository.save(task);
    }
}
