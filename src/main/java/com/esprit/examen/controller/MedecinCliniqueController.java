package com.esprit.examen.controller;


import com.esprit.examen.dto.Clinique;
import com.esprit.examen.dto.Medecin;
import com.esprit.examen.repository.CliniqueRepository;
import com.esprit.examen.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/esprit")
class MedecinCliniqueController {

    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    CliniqueRepository cliniqueRepository;

    @GetMapping("/cliniques")
    public ResponseEntity<List<Clinique>> getAllTutorials() {
        List<Clinique> cliniques = new ArrayList<Clinique>();
        cliniqueRepository.findAll().forEach(cliniques::add);

        if (cliniques.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cliniques, HttpStatus.OK);
    }


    @RequestMapping(value = "/cliniques",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<Clinique> addClinique(@RequestBody Clinique clinique) {
        Clinique _clinique = cliniqueRepository.save(new Clinique(clinique.getIdClinique(), clinique.getNomClinique(), clinique.getAdresse(), clinique.getMedecins()));
        return new ResponseEntity<>(_clinique, HttpStatus.CREATED);
    }

    @GetMapping("/medecins")
    public ResponseEntity<List<Medecin>> getAllTutorials(@RequestParam(required = false) Long clinique) {
        List<Medecin> medecins = new ArrayList<Medecin>();
        if (clinique == null)
            medecinRepository.findAll().forEach(medecins::add);
        else
            medecinRepository.findMedecinsByCliniques(clinique).forEach(medecins::add);
        if (medecins.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medecins, HttpStatus.OK);
    }


    @RequestMapping(value = "/medecins",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    public ResponseEntity<Medecin> addMedecin(@RequestBody Medecin medecin) {
        Medecin _medecin = medecinRepository.save(new Medecin(medecin.getIdMedecin(), medecin.getTelephone(), medecin.getTelephone(), medecin.getCliniques()));
        return new ResponseEntity<>(_medecin, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/medecins/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    public ResponseEntity<Medecin> addMedecinAndAssignToClinique(@RequestBody Medecin medecin, @PathVariable("id") Long cliniqueId) {
        Clinique clinique = cliniqueRepository.findById(cliniqueId).orElseThrow(() -> new ResourceNotFoundException("Clinique intruovable avec id = " + cliniqueId));
        ;
        if (medecin.getCliniques() == null) medecin.setCliniques(new HashSet<>());
        medecin.getCliniques().add(clinique);
        Medecin _medecin = medecinRepository.save(new Medecin(medecin.getIdMedecin(), medecin.getNomMedecin(), medecin.getTelephone(), medecin.getCliniques()));
        return new ResponseEntity<>(_medecin, HttpStatus.CREATED);


    }
}
