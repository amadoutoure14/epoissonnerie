package com.source.epoissonnerie.Assembleurs;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.VendeurPoisson;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class VendeurModelAssembleur implements RepresentationModelAssembler<VendeurPoisson, EntityModel<VendeurPoisson>> {

    @Override
    public EntityModel<VendeurPoisson> toModel(VendeurPoisson vendeur) {
        return EntityModel
                .of(vendeur,
                linkTo(methodOn(VendeurController.class)
                        .un(vendeur.getId())).withSelfRel(),
                linkTo(methodOn(VendeurController.class)
                        .tout()).withRel("vendeurs"));
    }
}