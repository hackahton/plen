package com.devs.hackaton.dto.Task.response;

import com.devs.hackaton.enums.TaskStatus;

public record UserReportResponse(

        String title,
        String description,
        TaskStatus status
) {
}
