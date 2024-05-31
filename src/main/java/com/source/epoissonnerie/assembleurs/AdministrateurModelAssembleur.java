package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.AdministrateurController;
import com.source.epoissonnerie.entites.Administrateur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AdministrateurModelAssembleur implements RepresentationModelAssembler<Administrateur, EntityModel<Administrateur>> {
    @Override
    public EntityModel<Administrateur> toModel(Administrateur administrateur) {
        return EntityModel
                .of(
                        administrateur,
                        linkTo(methodOn(AdministrateurController.class).un(administrateur.getId())).withSelfRel(),
                        linkTo(methodOn(AdministrateurController.class).liste()).withRel("Administrateurs")
                );
    }
}
