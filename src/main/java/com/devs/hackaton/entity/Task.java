package com.devs.hackaton.entity;

import com.devs.hackaton.enums.Difficulty;
import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

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

    @NotNull
    private String title;

    @NotNull
    private String description;

    @FutureOrPresent
    private LocalDate term;
    private Difficulty difficulty;
    private TaskStatus status;
    private Priority priority;

    @ManyToMany
    private List<User> users;
}
