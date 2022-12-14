package tn.esprit.spring.kaddem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repository.ContratRepository;
import tn.esprit.spring.kaddem.repository.EtudiantRepository;
import tn.esprit.spring.kaddem.service.IContratService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Tag(name = "Contrat Management")
@RestController
@AllArgsConstructor
@RequestMapping("/contrat")
@Slf4j
public class ContratRestController {

    IContratService contratService;
    ContratRepository contratRepository;
    EtudiantRepository etudiantRepository;

    @Operation(description = "retrieve all Contrats")
    @GetMapping("/retrieve-all-Contrats")
    public List<Contrat> getContrats() {
        long startDate= new Date().getTime();
        List<Contrat> listContrats = contratService.retrieveAllContrats();
        long endDate= new Date().getTime();
        log.info("Temps d execution: "+String.valueOf(endDate-startDate));
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
    @PostMapping("/affectContratToEtudiant")
    public Contrat affectContratToEtudiant(@RequestBody Contrat contrat, @RequestParam String nomE, @RequestParam String prenomE) {
        return contratService.affectContratToEtudiant(contrat, nomE, prenomE);
    }

    @Operation(description = "nb Contrats Valides")
    @GetMapping("/nbContratsValides")
    public Integer nbContratsValides(@RequestParam String startDate,@RequestParam String endDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date dateDebut = dateFormat.parse(startDate);
            Date dateFin = dateFormat.parse(endDate);
            return contratService.nbContratsValides(dateDebut, dateFin);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
