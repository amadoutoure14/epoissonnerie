package com.source.epoissonnerie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Adminstrateur")
public class Administrateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(
            regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+$",
            message = "Le nom doit contenir uniquement des lettres"
    )
    @Column(length = 200)
    private String nom;

    @NotNull
    @Pattern(
            regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+$",
            message = "Le prenom doit contenir uniquement des lettres"
    )
    @Column(length = 200)
    private String prenom;

    @NotNull
    @Column(unique = true, length = 8)
    private int tel;

    @NotNull
    @Column(unique = true,length = 100)
    @Email(message = "l'email ne pas correct")
    private String email;

    @NotNull
    @Size(min = 4,max = 20)
    @Column(length = 200)
    private String mdp;

    @Column(length = 5)
    private String role = "admin";

    @OneToMany(mappedBy = "administrateur",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Vendeur> Vendeurs;

    @OneToMany(mappedBy = "administrateur",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Client> clients;
}
