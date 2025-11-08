package com.devs.hackaton.dto.Tag.response;

import lombok.Builder;

@Builder
public record TagResponse(
        Long id,
        String name
) {
}
