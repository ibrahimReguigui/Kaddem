package tn.esprit.spring.kaddem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.service.IDepartementService;
import tn.esprit.spring.kaddem.service.IUniversiteService;

import java.util.List;

@Tag(name = "Universite Management")
@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {

    IUniversiteService universiteService;
    IDepartementService departementService;

    @Operation(description = "retrieve all Universites")
    @GetMapping("/retrieve-all-Universites")
    public List<Universite> getUniversites() {
        List<Universite> listUniversites = universiteService.retrieveAllUniversites();
        return listUniversites;
    }

    @Operation(description = "retrieve Universite")
    @GetMapping("/retrieve-Universite/{Universite-id}")
    public Universite retrieveUniversite(@PathVariable("Universite-id") Integer universiteId) {
        return universiteService.retrieveUniversite(universiteId);
    }

    @Operation(description = "add Universite")
    @PostMapping("/add-Universite")
    public Universite addUniversite(@RequestBody Universite e) {
        Universite universite = universiteService.addUniversite(e);
        return universite;
    }

    @Operation(description = "update Universite")
    @PutMapping("/update-Universite")
    public Universite updateUniversite(@RequestBody Universite e) {
        Universite universite = universiteService.updateUniversite(e);
        return universite;
    }

    @Operation(description = "assign Universite To Departement")
    @PutMapping("/assignUniversiteToDepartement")
    public void assignUniversiteToDepartement(@RequestParam Integer idUniversite, @RequestParam Integer idDepartement) {
        universiteService.assignUniversiteToDepartement(idUniversite, idDepartement);
    }
}
