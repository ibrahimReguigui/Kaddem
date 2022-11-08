package tn.esprit.spring.kaddem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repository.ContratRepository;
import tn.esprit.spring.kaddem.repository.EtudiantRepository;
import tn.esprit.spring.kaddem.service.IContratService;

import java.util.List;

@Tag(name = "Contrat Management")
@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
public class ContratRestController {

    IContratService contratService;
    ContratRepository contratRepository;
    EtudiantRepository etudiantRepository;

    @Operation(description = "retrieve all Contrats")
    @GetMapping("/retrieve-all-Contrats")
    public List<Contrat> getContrats() {
        List<Contrat> listContrats = contratService.retrieveAllContrats();
        return listContrats;
    }
    @Operation(description = "retrieve Contrat")
    @GetMapping("/retrieve-Contrat/{Contrat-id}")
    public Contrat retrieveContrat(@PathVariable("Contrat-id") Integer ContratId) {
        return contratService.retrieveContrat(ContratId);
    }
    @Operation(description = "add Contrat")
    @PostMapping("/add-Contrat")
    public Contrat addContrat(@RequestBody Contrat e) {
        Contrat contrat = contratService.addContrat(e);
        return contrat;
    }

    @Operation(description = "update Contrat")
    @PutMapping("/update-Contrat")
    public Contrat updateContrat(@RequestBody Contrat e) {
        Contrat Contrat = contratService.updateContrat(e);
        return Contrat;
    }

    @Operation(description = "affect Contrat To Etudiant")
    @PutMapping("/affectContratToEtudiant")
    public Contrat affectContratToEtudiant(@RequestBody Contrat contrat, @RequestBody String nomE, @RequestBody String prenomE) {
        return contratService.affectContratToEtudiant(contrat, nomE, prenomE);
    }
}
