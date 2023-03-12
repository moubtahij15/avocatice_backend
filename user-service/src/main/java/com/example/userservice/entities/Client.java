package com.example.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
public class Client extends User {
}
