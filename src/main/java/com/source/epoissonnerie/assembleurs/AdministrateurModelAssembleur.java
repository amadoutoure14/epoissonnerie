package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.AdministrateurController;
import com.source.epoissonnerie.dto.AdministrateurDTO;
import com.source.epoissonnerie.entites.Administrateur;
import com.source.epoissonnerie.mapper.AdministrateurMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class AdministrateurModelAssembleur implements RepresentationModelAssembler<Administrateur, EntityModel<AdministrateurDTO>> {
    final private AdministrateurMapper mapper;
    @Override
    public EntityModel<AdministrateurDTO> toModel(Administrateur administrateur) {

        AdministrateurDTO administrateurDTO = mapper.apply(administrateur);

        return EntityModel
                .of(
                        administrateurDTO,
                        linkTo(methodOn(AdministrateurController.class).un(administrateurDTO.id())).withSelfRel(),
                        linkTo(methodOn(AdministrateurController.class).liste()).withRel("Administrateurs")
                );
    }
}
