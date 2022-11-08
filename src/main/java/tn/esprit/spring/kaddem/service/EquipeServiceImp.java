package tn.esprit.spring.kaddem.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.repository.EquipeRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class EquipeServiceImp implements IEquipeService {

    private EquipeRepository equipeRepository;


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
    };
}
