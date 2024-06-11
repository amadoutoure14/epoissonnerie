package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.PublicationController;
import com.source.epoissonnerie.dto.PoissonDTO;
import com.source.epoissonnerie.dto.PublicationDTO;
import com.source.epoissonnerie.entites.Poisson;
import com.source.epoissonnerie.entites.Publication;
import com.source.epoissonnerie.mapper.PublicationMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class PublicationAssembleur implements RepresentationModelAssembler<Publication, EntityModel<PublicationDTO>> {
    private PublicationMapper mapper;
    @Override
    public EntityModel<PublicationDTO> toModel(Publication publication) {
        PublicationDTO publicationDTO = mapper.apply(publication);
        return EntityModel.of(
                publicationDTO,
                linkTo(methodOn(PublicationController.class).une(publicationDTO.id())).withSelfRel(),
                linkTo(methodOn(PublicationController.class).toute()).withRel("Publications")
        );
    }
}
