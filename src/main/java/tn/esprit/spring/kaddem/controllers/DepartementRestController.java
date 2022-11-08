package tn.esprit.spring.kaddem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.service.IDepartementService;
import tn.esprit.spring.kaddem.service.IEtudiantService;

import java.util.List;
@Tag(name = "Departement Management")
@RestController
@AllArgsConstructor
@RequestMapping("/departement")
public class DepartementRestController {

    IDepartementService departementService;
    IEtudiantService etudiantService;
    @Operation(description = "retrieve all Departements")
    @GetMapping("/retrieve-all-Departements")
    public List<Departement> getDepartements() {
        List<Departement> listDepartements = departementService.retrieveAllDepartements();
        return listDepartements;
    }
    @Operation(description = "retrieve Departement")
    @GetMapping("/retrieve-Departement/{Departement-id}")
    public Departement retrieveDepartement(@PathVariable("Departement-id") Integer departementId) {
        return departementService.retrieveDepartement(departementId);
    }
    @Operation(description = "add Departement")
    @PostMapping("/add-Departement")
    public Departement addDepartement(@RequestBody Departement e) {
        Departement departement = departementService.addDepartement(e);
        return departement;
    }
    @Operation(description = "update Departement")
    @PutMapping("/update-Departement")
    public Departement updateDepartement(@RequestBody Departement e) {
        Departement departement = departementService.updateDepartement(e);
        return departement;
    }

    @Operation(description = "assign Etudiant To Departement")
    @PutMapping("/assignEtudiantToDepartement")
    public void assignEtudiantToDepartement(@RequestParam Integer etudiantId,@RequestParam Integer departementId) {

        /*Departement departement = departementService.retrieveDepartement(departementId);
        Etudiant etudiant = etudiantService.retrieveEtudiant(etudiantId);
        departement.getEtudiantList().add(etudiant);
        System.out.println(departement.getEtudiantList().get(0).getNomE());
        departementService.updateDepartement(departement);*/
        departementService.assignEtudiantToDepartement(etudiantId,departementId);
    }
}
