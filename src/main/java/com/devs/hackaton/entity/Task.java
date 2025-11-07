package com.devs.hackaton.entity;

import com.devs.hackaton.enums.Difficulty;
import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id @GeneratedValue
    private UUID Id;

    private String title;
    private String description;
    private LocalDate term;
    private Difficulty difficulty;
    private TaskStatus status;
    private Priority priority;

    @ManyToMany
    private List<User> users;
}
