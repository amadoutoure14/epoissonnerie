package com.source.epoissonnerie.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double prix_total;

    @OneToMany(mappedBy = "commande",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Poisson> Poissons;

    @ManyToOne
    @JoinColumn(name = "panier")
    private Panier panier;
    @PrePersist
    public void prePersist() {
        this.prix_total = 0;
        for (Poisson poisson : Poissons) {
            this.prix_total += poisson.getPrixUnitaire();
        }
    }
}
