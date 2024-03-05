package com.source.epoissonnerie.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    @Column(unique = true)
    private int tel;
    @NotNull
    @Size(min = 4, max =20)
    private String mdp;

    private LocalDate date;

    private boolean actif;

    @OneToOne(mappedBy = "client")
    private Panier panier;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "administrateur")
    private Administrateur administrateur;

    @OneToMany(mappedBy = "client")
    private List<Evaluation> evaluations;

    @OneToMany(mappedBy = "client")
    private List<Commentaire> commentaires;

    @PrePersist
    public void PrePersist() {
        this.date = LocalDate.now();
        administrateur.setId(administrateur.getId());
    }

}
