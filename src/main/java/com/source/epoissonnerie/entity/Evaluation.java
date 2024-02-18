package com.source.epoissonnerie.entity;

import jakarta.persistence.*;
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
@Table(name = "Evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1,max = 6)
    private int note = 0;

    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;

    @OneToMany(mappedBy = "evaluation",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Poisson> poissons;
}
