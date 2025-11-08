package com.devs.hackaton.dto.Tag.request;

import java.util.UUID;

public record CreateTag(
        String nome,
        UUID userId
) {
}
