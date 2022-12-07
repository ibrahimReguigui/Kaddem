package tn.esprit.spring.kaddem.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repository.ContratRepository;
import tn.esprit.spring.kaddem.repository.EquipeRepository;
import tn.esprit.spring.kaddem.repository.EtudiantRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class EquipeServiceImp implements IEquipeService {

    private EquipeRepository equipeRepository;
    private EtudiantRepository etudiantRepository;
    private ContratRepository contratRepository;


    @Override
    public List<Equipe> retrieveAllEquipes() {
        return equipeRepository.findAll();
    }

    @Transactional
    public Equipe addEquipe(Equipe e) {
        return equipeRepository.save(e);
    }

    @Override
    public Equipe updateEquipe(Equipe e) {
        return equipeRepository.save(e);
    }

    @Override
    public Equipe retrieveEquipe(Integer idEquipe) {
        return equipeRepository.findById(idEquipe).get();
    }
    @Override
    public void deleteEquipe(Integer equipeId){
        equipeRepository.deleteById(equipeId);
    }

    @Override
   // @Scheduled(cron = "0/5 * * * * *")
    public void faireEvoluerEquipes() {
       List<Equipe> equipeList=equipeRepository.findAll();
        for (Equipe equipe:equipeList){
            int nbr=0;
            for(Etudiant etudiant:equipe.getEtudiantList()){
                if(membreDepasseUnAns(etudiant))
                    nbr++;
            }
            if(nbr>=3){
                switch (equipe.getNiveau()){
                    case SENIOR:
                        equipe.setNiveau(Niveau.EXPERT);
                        break;
                    case JUNIOR:
                        equipe.setNiveau(Niveau.SENIOR);
                        break;
                }
                equipeRepository.save(equipe);
            }
        }
    }

    public boolean membreDepasseUnAns(Etudiant etudiant){
        List<Contrat> contratList=contratRepository.findAll();
        List<Contrat> contratsEtudiantConcerné=new ArrayList<>();
        for(Contrat contrat:contratList){
            if(contrat.getEtudiant()==etudiant){
                contratsEtudiantConcerné.add(contrat);
            }
        }
        for(Contrat contrat:contratsEtudiantConcerné){
            if (contrat.getArchive()!=true){
                if(ChronoUnit.DAYS.between(contrat.getDateDebutContrat().toInstant(), LocalDate.now())>365){
                    System.out.println("1ans");
                    return true;
                }
            }
        }
        return false;
    }


}
