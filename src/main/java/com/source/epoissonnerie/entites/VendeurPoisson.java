package com.source.epoissonnerie.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Vendeur")
public class VendeurPoisson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nomComplet;

    @NotNull
    @Column(unique = true,length = 8)
    private int tel;

    @NotNull
    private String mdp;

    @NotNull
    private String adresse;

}
