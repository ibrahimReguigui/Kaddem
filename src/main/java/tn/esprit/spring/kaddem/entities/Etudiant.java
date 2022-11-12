package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Etudiant")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEtudiant")
    private Integer idEtudiant;

    private String prenomE;
    private String nomE;
    @Enumerated(EnumType.STRING)
    private Option op;
    @ManyToOne
    private Departement departement;
    @ManyToMany
    private List<Equipe> equipeList;

    @OneToMany(mappedBy = "etudiant")
    private List<Contrat> contratList;

}