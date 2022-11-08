package tn.esprit.spring.kaddem.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEquipe;
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
    @ManyToMany(mappedBy = "equipeList")
    private List<Etudiant> etudiantList;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private DetailEquipe detailEquipe;
}
