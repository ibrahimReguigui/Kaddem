package tn.esprit.spring.kaddem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.entities.Specialite;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {

    Etudiant findByNomEAndPrenomE(String nomE,String prenomE);
    List<Etudiant> findByDepartementIdDepartement(Integer idDepartement);

    List<Etudiant> findByEquipeListNiveau(Niveau niveau);

    @Query("select e from Etudiant e ,Contrat c where c.etudiant.idEtudiant=e.idEtudiant and c.specialite='SECURITE'")
    List<Etudiant> getSecuriteEtudiantsJPQL();

    @Query("select e from Etudiant e join Contrat c on c.etudiant.idEtudiant=e.idEtudiant where c.specialite='SECURITE'")
    List<Etudiant> getSecuriteEtudiantsSQL();


}
