package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.entites.Poisson;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

public class PoissonModelAssembleur implements RepresentationModelAssembler<Poisson, EntityModel<Poisson>> {
    @Override
    public EntityModel<Poisson> toModel(Poisson poisson) {
        return null;
    }
}
