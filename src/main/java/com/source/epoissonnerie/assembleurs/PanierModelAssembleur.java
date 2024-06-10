package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.PanierController;

import com.source.epoissonnerie.dto.PanierDTO;
import com.source.epoissonnerie.entites.Panier;

import com.source.epoissonnerie.mapper.PanierMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class PanierModelAssembleur implements RepresentationModelAssembler<Panier, EntityModel<PanierDTO>> {
    final private PanierMapper mapper;
    @Override
    public EntityModel<PanierDTO> toModel(Panier panier) {
        PanierDTO panierDTO = mapper.apply(panier);
        return EntityModel
                .of(
                        panierDTO,
                        linkTo(methodOn(PanierController.class).un(panierDTO.id())).withSelfRel(),
                        linkTo(methodOn(PanierController.class).liste()).withRel("paniers")
                );
    }
}
