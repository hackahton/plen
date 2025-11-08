package com.devs.hackaton.service;

import com.devs.hackaton.dto.Task.request.*;
import com.devs.hackaton.dto.Task.response.TaskResponse;
import com.devs.hackaton.entity.Task;
import com.devs.hackaton.entity.User;
import com.devs.hackaton.enums.*;
import com.devs.hackaton.repository.TaskRepository;
import com.devs.hackaton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


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

    public void editarTask(MudancaRequest request){
        Task task = taskRepository.findById(request.idTask())
                .orElseThrow(()-> new IllegalArgumentException("Nao encontrado"));

        for (Mudanca mudanca : request.mudancas() ) {
            switch (mudanca.tipoMundaca()){
                case TipoMundaca.STATUS -> task.setStatus(mudanca.status());
                case TipoMundaca.DESCRICAO -> task.setDescription(mudanca.descricao());
                case TipoMundaca.DIFICUDADE -> task.setDifficulty(mudanca.difficulty());
                case TipoMundaca.PRAZO -> task.setTerm(mudanca.prazo());
                case TipoMundaca.TITULO -> task.setTitle(mudanca.titulo());
                case TipoMundaca.PRIORIDADE -> task.setPriority(mudanca.prioridade());
                default -> throw new IllegalArgumentException("Tipo de mundanca nao encontrado");
            }
        }

        taskRepository.save(task);
    }

    public void atribuirTask(AtribuicaoRequest request){
        Task task = taskRepository.findById(request.idTask())
                .orElseThrow(() -> new IllegalArgumentException("Task nao encontrada"));

        User user = userRepository.findById(request.idUser())
                .orElseThrow(() -> new IllegalArgumentException("User nao encontrado"));

        if (user.getStatus().equals(Company_User_Status.INACTIVE)){
            task.getUsers().add(userRepository.findFirstByStatus(Company_User_Status.ACTIVE));
        }

        task.getUsers().add(user);
        user.getTasks().add(task);

        taskRepository.save(task);
        userRepository.save(user);
    }

    public void fazerComentario(ComentarioRequest request){
        Task task = taskRepository.findById(request.idTask())
                .orElseThrow(() -> new IllegalArgumentException("Task nao encontrada"));

        task.getComentario().add(request.comentario());

        taskRepository.save(task);
    }
}
