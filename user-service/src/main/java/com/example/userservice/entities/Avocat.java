package com.example.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Avocat extends User {
    private String spécialité;
    private boolean consultation_tel;
    private boolean consultation_ecrit;

}
