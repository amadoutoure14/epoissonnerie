package com.source.epoissonnerie.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Poisson")
public class Poisson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 200)
    private String nom;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypePoisson type;

    @NotNull
    @Min(value = 1)
    private int quantite;

    @NotNull
    @Column(nullable = false)
    private double prixUnitaire;

    private boolean publier = false;

    @ManyToOne
    @JoinColumn(name = "collection")
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "commande")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "commentaire")
    private Commentaire commentaire;

    @ManyToOne
    @JoinColumn(name = "evaluation")
    private Evaluation evaluation;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy",timezone = "UTC")
    private LocalDate date;
    @PrePersist
    public void PrePresist() {
        this.date = LocalDate.now();
    }
}
