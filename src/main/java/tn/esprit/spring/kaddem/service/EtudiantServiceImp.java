
package tn.esprit.spring.kaddem.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repository.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImp implements IEtudiantService {


    private EtudiantRepository etudiantRepository;

    private EquipeServiceImp equipeServiceImp;

    private ContratServiceImp contratServiceImp;

    @Override
    public void removeEtudiant(Integer idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);
    }


    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(Integer idEtudiant) {
        return etudiantRepository.findById(idEtudiant).get();
    }

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Transactional
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
        if(e.getEquipeList()==null){
            List list=new ArrayList<Equipe>();
            list.add(equipeServiceImp.retrieveEquipe(idEquipe));
            e.setEquipeList(list);
        }else{
            e.getEquipeList().add(equipeServiceImp.retrieveEquipe(idEquipe));
        }
        Contrat c=contratServiceImp.retrieveContrat(idContrat);
        c.setEtudiant(e);
        contratServiceImp.updateContrat(c);
        etudiantRepository.save(e);
        return e;
    }

    @Override
    public List<Etudiant> getEtudiantsByDepartement(Integer idDepartement) {
        return etudiantRepository.findByDepartementIdDepartement(idDepartement);
    }
}

