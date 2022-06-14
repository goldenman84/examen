package com.esprit.examen.repository;

import com.esprit.examen.dto.Clinique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CliniqueRepository  extends JpaRepository<Clinique, Long> {
}
