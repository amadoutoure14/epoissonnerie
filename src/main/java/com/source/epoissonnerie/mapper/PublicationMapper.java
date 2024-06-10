package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.PublicationDTO;
import com.source.epoissonnerie.entites.Publication;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class PublicationMapper implements Function<Publication ,PublicationDTO> {
    @Override
    public PublicationDTO apply(Publication publication) {
        return new PublicationDTO(publication.getId(),publication.getTitre(),publication.getDate());
    }
}
