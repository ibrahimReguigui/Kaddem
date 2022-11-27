package tn.esprit.spring.kaddem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.Contrat;

import java.util.Date;
import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat,Integer> {
//    Integer countByDateFinContratIsBetweenanAndAndArchiveIsNot(Date startDate, Date endDate,Boolean status);
    @Query(value = "select count(c) from Contrat c where c.dateDebutContrat>?1 and c.dateFinContrat<?2 and c.archive=?3")
    Integer nbContratsValides(Date startDate, Date endDate,boolean statut);


}
