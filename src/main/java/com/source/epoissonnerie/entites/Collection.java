package com.source.epoissonnerie.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nom;

    @NotNull
    private String description;

    private boolean publier;

    @ManyToOne
    @JoinColumn(name = "vendeur")
    private Vendeur vendeur;

    @OneToMany(mappedBy = "collection",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Poisson> poissons;
}