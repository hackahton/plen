package com.devs.hackaton.dto.Task.request;

import java.util.UUID;

public record AtribuicaoRequest(
        UUID idTask,
        UUID idUser
) {
}
