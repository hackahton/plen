package com.devs.hackaton.dto.Task.request;

import com.devs.hackaton.enums.Difficulty;
import com.devs.hackaton.enums.Priority;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record TaskRequest(
        String title,
        String description,
        LocalDate term,
        Difficulty difficulty,
        Priority priority,
        UUID user_id
) {
}
