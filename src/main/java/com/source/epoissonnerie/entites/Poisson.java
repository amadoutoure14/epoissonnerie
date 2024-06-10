package com.source.epoissonnerie.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Poisson")
public class Poisson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypePoisson type;

    private String description;

    @NotNull
    @Min(value = 1)
    private int quantite;

    @NotNull
    @Column(nullable = false)
    private double prix;

    private boolean publier;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy",timezone = "UTC")
    private Date date;

    @ManyToOne
    @NotNull
    private Categorie categorie;

    @ManyToOne
    private Commande commande;


    @OneToMany
    private List<Evaluation> evaluations;

    @OneToMany
    private List<Commentaire> commentaires;

    @PrePersist
    private void onCreated(){
        date = new Date();
    }

}
