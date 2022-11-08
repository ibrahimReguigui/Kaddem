package tn.esprit.spring.kaddem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.Contrat;
@Repository
public interface ContratRepository extends JpaRepository<Contrat,Integer> {
}
