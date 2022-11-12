package tn.esprit.spring.kaddem.service;

import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;

import java.util.List;

public interface IDepartementService {
    List<Departement> retrieveAllDepartements();

    Departement addDepartement (Departement d);

    void assignEtudiantToDepartement (Integer etudiantId, Integer departementId) ;
    Departement updateDepartement (Departement d);

    Departement retrieveDepartement (Integer idDepart);

    List<Etudiant> getEtudiantsByDepartement (Integer idDepartement);
}
