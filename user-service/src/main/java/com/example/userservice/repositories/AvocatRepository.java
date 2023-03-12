package com.example.userservice.repositories;

import com.example.userservice.entities.Avocat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AvocatRepository extends JpaRepository<Avocat, String> {
}
