package com.devs.hackaton.dto.Tag.request;

import java.util.UUID;

public record CreateTag(
        String name,
        UUID userId
) {
}
