package com.devs.hackaton.dto.request;

import com.devs.hackaton.enums.Difficulty;
import com.devs.hackaton.enums.Priority;

import java.time.LocalDate;
import java.util.UUID;

public record TaskRequest(
        String title,
        String description,
        LocalDate term,
        Difficulty difficulty,
        Priority priority,
        UUID user_id
) {
}
