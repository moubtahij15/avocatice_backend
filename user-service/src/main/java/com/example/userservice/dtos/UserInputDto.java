package com.example.userservice.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInputDto {
    private String email;
    private String password;
    private String nom;
    private String prénom;
    private String cin;
    private String gender;
    private String image_url;
    private String ville;
    private String spécialité;
    private String role;
    private boolean consultation_tel;
    private boolean consultation_ecrit;

    private String createdAt = LocalDateTime.now().toString();



    public UserInputDto() {
    }

    //login
    public UserInputDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //   register avocat
    public UserInputDto(String email, String password, String nom, String prénom, String cin, String gender, String image_url, String ville, String spécialité, String role, boolean consultation_tel, boolean consultation_ecrit) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prénom = prénom;
        this.cin = cin;
        this.gender = gender;
        this.image_url = image_url;
        this.ville = ville;
        this.spécialité = spécialité;
        this.role = role;
        this.consultation_tel = consultation_tel;
        this.consultation_ecrit = consultation_ecrit;
    }

    //    agent & client register
    public UserInputDto(String email, String password, String nom, String prénom, String cin, String gender, String image_url, String ville, String role) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prénom = prénom;
        this.cin = cin;
        this.gender = gender;
        this.image_url = image_url;
        this.ville = ville;
        this.role = role;
    }

}
