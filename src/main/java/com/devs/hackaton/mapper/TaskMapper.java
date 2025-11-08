package com.devs.hackaton.mapper;

import com.devs.hackaton.dto.Task.response.TaskResponse;
import com.devs.hackaton.entity.Task;

import java.util.List;

public class TaskMapper {
    public static TaskResponse toTaskResponse(Task task){
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .term(task.getTerm())
                .difficulty(task.getDifficulty())
                .status(task.getStatus())
                .priority(task.getPriority())
                .build();
    }

    public static List<TaskResponse> toTaskResponseList(List<Task> tasks){
        return tasks.stream()
                .map(TaskMapper::toTaskResponse)
                .toList();
    }
}
