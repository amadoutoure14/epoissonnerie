package com.source.epoissonnerie.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "visiteur")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Visiteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @PrePersist
    protected void onCreate() {
        Date date = new Date();
    }
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",timezone = "UTC")
    private Date date;

}
