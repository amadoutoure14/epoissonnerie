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
    private Long id;

    @NotNull
    private String nom,mdp;

    @NotNull
    @Column(unique = true, length = 8)
    private int tel;

    @NotNull
    @Email
    private String email;

}
