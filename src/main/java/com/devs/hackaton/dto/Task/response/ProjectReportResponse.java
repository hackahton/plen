package com.devs.hackaton.dto.Task.response;

import com.devs.hackaton.enums.TaskStatus;
import lombok.Builder;

@Builder
public record ProjectReportResponse(
        String title,
        String description,
        TaskStatus status
) {
}
