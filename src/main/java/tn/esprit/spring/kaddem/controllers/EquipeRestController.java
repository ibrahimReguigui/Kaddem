package tn.esprit.spring.kaddem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.service.IEquipeService;

import java.util.List;

@Tag(name = "Equipe Management")
@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {
    IEquipeService equipeService;

    @Operation(description = "retrieve all equipes")
    // http://localhost:8089/Kaddem/equipe/retrieve-all-equipes
    @GetMapping("/retrieve-all-equipes")
    public List<Equipe> getEquipes() {
        List<Equipe> listEquipes = equipeService.retrieveAllEquipes();
        return listEquipes;
    }

    @Operation(description = "retrieve equipe")
    // http://localhost:8089/Kaddem/equipe/retrieve-equipe/8
    @GetMapping("/retrieve-equipe/{equipe-id}")
    public Equipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
        return equipeService.retrieveEquipe(equipeId);
    }

    @Operation(description = "add equipe")
    // http://localhost:8089/Kaddem/equipe/add-equipe
    @PostMapping("/add-equipe")
    public Equipe addEquipe(@RequestBody Equipe e) {
        Equipe equipe = equipeService.addEquipe(e);
        return equipe;
    }

    @Operation(description = "remove equipe")
    // http://localhost:8089/Kaddem/equipe/remove-equipe/1
    @DeleteMapping("/remove-equipe/{equipe-id}")
    public void removeOperateur(@PathVariable("equipe-id") Integer equipeId) {
        equipeService.deleteEquipe(equipeId);
    }

    @Operation(description = "update equipe")
    // http://localhost:8089/Kaddem/equipe/update-equipe
    @PutMapping("/update-equipe")
    public Equipe updateEtudiant(@RequestBody Equipe e) {
        Equipe equipe = equipeService.updateEquipe(e);
        return equipe;
    }
}

