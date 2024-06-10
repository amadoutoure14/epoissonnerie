package com.source.epoissonnerie.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    @OneToOne
    @JoinColumn(name = "vendeur")
    private Vendeur vendeur;

    private Date date;
    @PrePersist
    private void prePersist() {
        this.date = new Date();
    }
}
