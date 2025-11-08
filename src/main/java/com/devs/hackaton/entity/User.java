package com.devs.hackaton.entity;

import com.devs.hackaton.enums.Role;
import com.devs.hackaton.enums.Company_User_Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    @Size(min = 6)
    private String password;

    @Column(nullable = false)
    @CPF
    @Size(min = 11)
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Company_User_Status status;

    @ManyToMany(mappedBy = "users")
    private List<Project> projects = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Task> tasks = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Company company;

    @ManyToMany(mappedBy = "user")
    private List<Tag> tags = new ArrayList<>();
}
