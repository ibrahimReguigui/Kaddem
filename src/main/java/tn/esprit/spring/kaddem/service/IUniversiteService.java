package tn.esprit.spring.kaddem.service;

import tn.esprit.spring.kaddem.entities.Universite;

import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversites();
    Universite addUniversite (Universite u);
    Universite updateUniversite (Universite u);
    Universite retrieveUniversite (Integer idUniversite);
    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement);
}
