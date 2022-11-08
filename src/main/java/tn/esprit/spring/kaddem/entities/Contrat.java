package tn.esprit.spring.kaddem.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contrat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idContrat;
    @Temporal(TemporalType.DATE)
    Date dateDebutContrat;
    @Temporal(TemporalType.DATE)
    Date dateFinContrat;
    Specialite specialite;
    @Column(nullable = false)
    Boolean archive;
    Integer montantContrat;
    @ManyToOne
    Etudiant etudiant;

}
