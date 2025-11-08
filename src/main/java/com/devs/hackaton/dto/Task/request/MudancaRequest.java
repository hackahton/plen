package com.devs.hackaton.dto.Task.request;


import java.util.List;
import java.util.UUID;

public record MudancaRequest(
        UUID idTask,
        List<Mudanca> mudancas
) {
}
