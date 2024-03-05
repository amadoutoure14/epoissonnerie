package com.source.epoissonnerie.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "Vendeur")
public class Vendeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 200)
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+$",message = "Le nom doit contenir uniquement des lettres")
    private String nom;

    @NotNull
    @Pattern(regexp = "^[a-zA-ZÀ-ÖØ-öø-ÿ]+$", message = "Le prenom doit contenir uniquement des lettres")
    @Column(length = 200)
    private String prenom;

    @NotNull
    @Column(unique = true,length = 8)
    private int tel;

    private boolean actif;

    @NotNull
    @Size(min = 4,max = 20)
    @Column(length = 200)
    private String mdp;


    @OneToMany(mappedBy = "vendeur",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Collection> collections;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "administrateur")
    private Administrateur administrateur;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy",timezone = "UTC")
    private LocalDate date;

    @PrePersist
    public void PrePersist() {
        this.date = LocalDate.now();
        administrateur.setId(administrateur.getId());
    }
}
