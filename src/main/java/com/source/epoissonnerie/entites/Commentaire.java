package com.source.epoissonnerie.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
    @Table(name = "Commentaire")
    public class Commentaire {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull
        @Min(value = 20,message = "Le commentaire est trop court")
        private String contenu;

        @Temporal(TemporalType.DATE)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone = "UTC")
        private LocalDate date;

        @ManyToOne
        @JoinColumn(name = "client")
        private Client client;

        @OneToMany(mappedBy = "commentaire",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
        private List<Poisson> poissons;
        @PrePersist
        public void PrePersist() {
            this.date = LocalDate.now();
        }
    }
