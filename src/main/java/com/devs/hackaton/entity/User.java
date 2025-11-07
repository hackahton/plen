package com.devs.hackaton.entity;

import com.devs.hackaton.enums.Role;
import com.devs.hackaton.enums.Company_User_Status;
import jakarta.persistence.*;
import lombok.*;

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
    private String name;
    private String email;
    private String password;
    private String cpf;
    private Role role;
    private Company_User_Status status;
}
