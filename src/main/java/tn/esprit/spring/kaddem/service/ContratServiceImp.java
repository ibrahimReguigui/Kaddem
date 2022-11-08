package tn.esprit.spring.kaddem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repository.ContratRepository;
import tn.esprit.spring.kaddem.repository.EtudiantRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ContratServiceImp implements IContratService {

    private ContratRepository contratRepository;
    private EtudiantRepository etudiantRepository;

    @Override
    public Contrat addContrat(Contrat ce) {
        return contratRepository.save(ce);
    }

    @Override
    public List<Contrat> retrieveAllContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Contrat updateContrat(Contrat ce) {
        return contratRepository.save(ce);
    }

    @Override
    public Contrat retrieveContrat(Integer idContrat) {
        return contratRepository.findById(idContrat).get();
    }

    @Override
    public void removeContrat(Integer idContrat) {
        contratRepository.deleteById(idContrat);
    }

    @Override
    public Contrat affectContratToEtudiant(Contrat ce, String nomE, String prenomE) {
        Etudiant etudiant = etudiantRepository.findByNomEAndPrenomE(nomE, prenomE);
        if (etudiant != null) {
            int nombreContratActif = 0;
            for (Contrat contrat : etudiant.getContratList()) {
                if (contrat.getArchive() == true)
                    nombreContratActif++;
            }
            if (nombreContratActif < 5) {
                ce.setEtudiant(etudiant);
                ce.setArchive(true);
                updateContrat(ce);
            }
        }
        return ce;
    }
}