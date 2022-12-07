package tn.esprit.spring.kaddem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Universite;
@Repository
public interface UniversiteRepository extends JpaRepository<Universite,Integer> {


}
