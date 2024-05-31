package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.PoissonController;
import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Poisson;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PoissonModelAssembleur implements RepresentationModelAssembler<Poisson, EntityModel<Poisson>> {
    @Override
    public EntityModel<Poisson> toModel(Poisson poisson) {
        return EntityModel
                .of(
                        poisson,
                        linkTo(methodOn(PoissonController.class).un(poisson.getId())).withSelfRel(),
                        linkTo(methodOn(PoissonController.class).tout()).withRel("poissons")
                );
    }
}
