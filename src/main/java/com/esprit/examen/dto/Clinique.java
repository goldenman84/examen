package com.esprit.examen.dto;

import lombok.*;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name = "clinique")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Clinique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idClinique;

    @Basic(optional = false)
    @Column(name = "nom_clinique")
    private String nomClinique;

    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;


    @ManyToMany(mappedBy = "cliniques")
    private Set<Medecin> medecins;
}
