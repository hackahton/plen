package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Task.request.TaskRequest;
import com.devs.hackaton.dto.Task.response.TaskResponse;
import com.devs.hackaton.entity.Task;

import java.util.List;

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

    public static TaskResponse toTaskResponse(Task request){
        return TaskResponse.builder()
                .id(request.getId())
                .title(request.getTitle())
                .description(request.getDescription())
                .term(request.getTerm())
                .difficulty(request.getDifficulty())
                .status(request.getStatus())
                .priority(request.getPriority())
                .build();
    }

    public static List<TaskResponse> toListTaskResponse(List<Task> tasks) {
        return tasks.stream()
                .map(TaskMapper::toTaskResponse)
                .toList();
    }
}
