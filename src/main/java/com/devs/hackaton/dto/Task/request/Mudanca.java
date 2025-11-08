package com.devs.hackaton.dto.Task.request;

import com.devs.hackaton.enums.Difficulty;
import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.TaskStatus;
import com.devs.hackaton.enums.TipoMundaca;

import java.time.LocalDate;

public record Mudanca(
        TipoMundaca tipoMundaca,

        String titulo,
        String descricao,
        LocalDate prazo,
        Difficulty difficulty,
        Priority prioridade,
        TaskStatus status
) {
}
