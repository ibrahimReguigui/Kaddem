package tn.esprit.spring.kaddem.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repository.DepartementRepository;
import tn.esprit.spring.kaddem.repository.EtudiantRepository;

import java.util.List;


@Service
@AllArgsConstructor
public class DepartementServiceImp implements IDepartementService {

    private DepartementRepository departementRepository;
    private IEtudiantService etudiantService;

    @Override
    public List<Departement> retrieveAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement addDepartement(Departement d) {
        return departementRepository.save(d);
    }

    @Override
    public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
        Etudiant etudiant=etudiantService.retrieveEtudiant(etudiantId);
        etudiant.setDepartement(retrieveDepartement(departementId));
        etudiantService.updateEtudiant(etudiant);
    }
    @Override
    public Departement updateDepartement(Departement d) {
        return departementRepository.save(d);
    }

    @Override
    public Departement retrieveDepartement(Integer idDepart) {
        return departementRepository.findById(idDepart).get();
    }
}
