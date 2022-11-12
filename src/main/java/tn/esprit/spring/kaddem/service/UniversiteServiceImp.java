package tn.esprit.spring.kaddem.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repository.UniversiteRepository;

import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class UniversiteServiceImp implements IUniversiteService {

    private UniversiteRepository universiteRepository;

    private DepartementServiceImp departementServiceImp;


    @Override
    public List<Universite> retrieveAllUniversites() {
        return universiteRepository.findAll();
    }

    @Transactional
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversite(Integer idUniversite) {
        return universiteRepository.findById(idUniversite).get();
    }

    @Override
    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement) {
        Universite universite=retrieveUniversite(idUniversite);
        universite.getDepartementList().add(departementServiceImp.retrieveDepartement(idDepartement));
        updateUniversite(universite);
    }

    @Override
    public List<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        return retrieveUniversite(idUniversite).getDepartementList();
    }

    @Override
    public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
        return 0;
    }
}
