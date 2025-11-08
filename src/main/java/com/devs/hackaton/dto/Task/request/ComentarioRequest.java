package com.devs.hackaton.dto.Task.request;

import java.util.UUID;

public record ComentarioRequest(
        UUID idTask,
        String comentario,
        UUID idUser
) {
}
