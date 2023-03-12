package com.example.userservice.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserOutPutDto {
    private String email;
    private String nom;
    private String prénom;
    private String cin;
    private String gender;
    private String image_url;
    private String ville;
    private String spécialité;
    private String role;
    private String status;
    private String createdAt = LocalDateTime.now().toString();

}
