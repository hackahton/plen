package com.devs.hackaton.dto.Task.response;

import com.devs.hackaton.enums.Difficulty;
import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.TaskStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record TaskResponse(
    UUID id,
    String title,
    String description,
    LocalDate term,
    Difficulty difficulty,
    TaskStatus status,
    Priority priority
) {
}
