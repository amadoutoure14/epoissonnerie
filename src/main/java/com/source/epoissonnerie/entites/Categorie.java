package com.source.epoissonnerie.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
@Table(name = "Categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String nom;

    private String description;

    private boolean publier;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone = "UTC")
    private Date date;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "vendeur")
    private Vendeur vendeur;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Poisson> poissons;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "publication")
    private Publication publication;

    @PrePersist
    protected void onCreate() {
        this.date = new Date();
    }
}