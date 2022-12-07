package tn.esprit.spring.kaddem.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repository.ContratRepository;
import tn.esprit.spring.kaddem.repository.EtudiantRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j
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
            System.out.println("etudiant existe");
            int nombreContratActif = 0;
            for (Contrat contrat : etudiant.getContratList()) {
                if (contrat.getArchive() != false)
                    nombreContratActif++;
            }
            if (nombreContratActif < 5) {
                ce.setEtudiant(etudiant);
                ce.setArchive(true);
                updateContrat(ce);
            }
        } else
            System.out.println("etudiant non existant");
        return ce;
    }


    @Override
    public Integer nbContratsValides(Date startDate, Date endDate) {
        return contratRepository.nbContratsValides(startDate, endDate, !true);
    }

    //@Override
   // @Scheduled(cron = "*/5 * * * * *")
    /*   public void retrieveAndUpdateStatusContrat() {
           List<Contrat> contrat = contratRepository.findAll();
           for (Contrat c : contrat) {
               Long remainingDays = ChronoUnit.DAYS.between(LocalDate.of(c.getDateFinContrat().getYear(),c.getDateFinContrat().getMonth()
                       ,c.getDateFinContrat().getDay()),LocalDate.now());
               System.out.println(remainingDays);
               if (remainingDays <= 15) {
                   System.out.println(c);
                   if (remainingDays <= 0) {
                       c.setArchive(true);
                       contratRepository.save(c);
                   }
               }
           }
       }
       */
    @Override
   // @Scheduled(cron = "0 0 1 ? * *")
    public void retrieveAndUpdateStatusContrat() {
        List<Contrat> C = contratRepository.findAll();
        LocalDate localDate = LocalDate.now();
        for (int d=0 ; d<C.size() ; d++){
            Contrat S=  C.get(d);
            long diff=localDate.getDayOfMonth()-S.getDateFinContrat().getTime();
            long diffs = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            if (diffs<=0){
                log.info("Contrat "+ LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
                S.setArchive(true);
                contratRepository.save(S);}
            else if (diffs <=15){
                log.info("Contrat expirer "+ LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
            }
        }
    }

}
