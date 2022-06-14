package com.esprit.examen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "medecin")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Medecin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMedecin;

    @Basic(optional = false)
    @Column(name = "nom_medecin")
    private String nomMedecin;

    @Basic(optional = false)
    @Column(name = "telephone")
    private String telephone;


    @ManyToMany
    @JoinTable(
            name = "clinique_medecin",
            joinColumns = @JoinColumn(name = "id_medecin"),
            inverseJoinColumns = @JoinColumn(name = "id_clinique"))
    private Set<Clinique> cliniques;

}
