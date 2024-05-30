package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.CategorieController;
import com.source.epoissonnerie.entites.Categorie;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategorieModelAssembleur implements RepresentationModelAssembler<Categorie, EntityModel<Categorie>> {
    @Override
    public EntityModel<Categorie> toModel(Categorie categorie) {
        return EntityModel
                .of(
                        categorie,
                linkTo(methodOn(CategorieController.class).une(categorie.getId())).withSelfRel(),
                        linkTo(methodOn(CategorieController.class).toute()).withRel("collections")
        );
    }
}
