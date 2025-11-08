package com.devs.hackaton.entity;

import com.devs.hackaton.enums.Priority;
import com.devs.hackaton.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_status")
    private ProjectStatus projectStatus;


    @ManyToMany(mappedBy = "projects")
    @Column(name = "company_id")
    private List<Company> companies;

    @ManyToMany
    @JoinTable(name = "project_user",joinColumns = @JoinColumn(name = "project_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "user_id")
    private List<User> users;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
}