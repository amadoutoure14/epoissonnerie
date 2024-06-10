package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.AdministrateurDTO;
import com.source.epoissonnerie.entites.Administrateur;
import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class AdministrateurMapper implements Function<Administrateur, AdministrateurDTO> {
    @Override
    public AdministrateurDTO apply(Administrateur administrateur) {
        return new AdministrateurDTO(administrateur.getId(),administrateur.getNom(),administrateur.getMdp(),administrateur.getTel(),administrateur.getEmail());
    }
}
