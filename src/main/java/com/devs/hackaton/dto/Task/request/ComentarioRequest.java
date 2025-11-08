package com.devs.hackaton.dto.Task.request;

import com.devs.hackaton.entity.Comentario;

import java.util.UUID;

public record ComentarioRequest(
        UUID idTask,
        Comentario comentario,
        UUID idUser
) {
}
