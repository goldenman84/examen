package com.esprit.examen.repository;

import com.esprit.examen.dto.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    List<Medecin> findMedecinsByCliniques(Long tagId);
}
