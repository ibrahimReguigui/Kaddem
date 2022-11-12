package tn.esprit.spring.kaddem.service;

import tn.esprit.spring.kaddem.entities.Contrat;

import java.util.Date;
import java.util.List;

public interface IContratService {
    Contrat addContrat (Contrat ce);
    List<Contrat> retrieveAllContrats();

    Contrat updateContrat (Contrat ce);

    Contrat retrieveContrat (Integer idContrat);

    void removeContrat(Integer idContrat);

    Contrat affectContratToEtudiant (Contrat ce, String nomE,String prenomE);
    Integer nbContratsValides(Date startDate, Date endDate);
}
