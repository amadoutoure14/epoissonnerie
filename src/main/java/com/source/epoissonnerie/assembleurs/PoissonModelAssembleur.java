package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.PoissonController;

import com.source.epoissonnerie.dto.PoissonDTO;
import com.source.epoissonnerie.entites.Poisson;

import com.source.epoissonnerie.mapper.PoissonMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
@AllArgsConstructor
public class PoissonModelAssembleur implements RepresentationModelAssembler<Poisson, EntityModel<PoissonDTO>> {
    final private PoissonMapper mapper;
    @Override
    public EntityModel<PoissonDTO> toModel(Poisson poisson) {
        PoissonDTO poissonDTO = mapper.apply(poisson);
        return EntityModel
                .of(
                        poissonDTO,
                        linkTo(methodOn(PoissonController.class).un(poissonDTO.id())).withSelfRel(),
                        linkTo(methodOn(PoissonController.class).liste()).withRel("poissons")
                );
    }
}
