package com.devs.hackaton.dto.request;

import java.util.UUID;

public record CreateTag(
        String nome,
        UUID userId
) {
}
