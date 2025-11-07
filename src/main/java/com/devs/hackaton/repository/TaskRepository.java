package com.devs.hackaton.repository;
import com.devs.hackaton.entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository  extends JpaRepository<Task, UUID> {
}
