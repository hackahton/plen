package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Task.request.TaskRequest;
import com.devs.hackaton.entity.Task;

public class TaskMapper {
    public static Task toEntity(TaskRequest request){
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .term(request.term())
                .difficulty(request.difficulty())
                .priority(request.priority())
                .build();
    }
}
