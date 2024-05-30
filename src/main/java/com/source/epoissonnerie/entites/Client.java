package com.source.epoissonnerie.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 200)
    private String nom_complet;

    @NotNull
    @Column(unique = true,length = 8)
    private int tel;

    @NotNull
    @Size(min = 4,max = 20)
    private String mdp;
    
    private LocalDate date;


}
