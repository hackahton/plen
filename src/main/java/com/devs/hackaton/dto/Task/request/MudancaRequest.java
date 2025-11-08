package com.devs.hackaton.dto.Task.request;

import com.devs.hackaton.enums.TaskStatus;

import java.util.UUID;

public record MudancaRequest(
        UUID idUser,
        UUID idTask,
        TaskStatus status
) {
}
