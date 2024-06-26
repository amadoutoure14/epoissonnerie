package com.source.epoissonnerie.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Adminstrateur")
public class Administrateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nomComplet;

    @NotNull
    @Column(unique = true, length = 8)
    private int tel;

    @NotNull
    private String email;

    @NotNull
    private String mdp;
}
