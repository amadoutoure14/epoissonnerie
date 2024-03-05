package com.source.epoissonnerie.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Panier")
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String nom;

    @NotNull
    @Min(10L)
    private String description;

    @Column(nullable = false)
    private double prix_total;

    private int quantite;

    @OneToOne
    @JoinColumn(name = "client")
    private Client client;

    @OneToMany(mappedBy = "panier",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Commande> commandes;
    @PrePersist
    public void prePersist(){
        this.prix_total = 0;
        for (Commande commande : commandes) {
            this.prix_total += commande.getPrix_total();
        }
    }
}
