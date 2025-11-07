package com.devs.hackaton.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class HourRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private double hours;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}
