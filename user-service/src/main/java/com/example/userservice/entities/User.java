package com.example.userservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String email;
    @Transient
    private String password;
    private String nom;
    private String prénom;
    private String cin;
    private String gender;
    private String image_url;
    private String ville;
    private String role;
    private boolean emailConfirmation;
    private boolean status;
    private String createdAt = LocalDateTime.now().toString();

}
