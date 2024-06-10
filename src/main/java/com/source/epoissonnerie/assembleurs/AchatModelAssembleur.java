package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.AchatController;
import com.source.epoissonnerie.dto.AchatDTO;
import com.source.epoissonnerie.entites.Achat;
import com.source.epoissonnerie.mapper.AchatMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class AchatModelAssembleur implements RepresentationModelAssembler<Achat, EntityModel<AchatDTO>> {
    final private AchatMapper mapper;
    @Override
    public EntityModel<AchatDTO> toModel(Achat achat) {

        AchatDTO achatDTO = mapper.apply(achat);

        return EntityModel
                .of(
                        achatDTO,
                        linkTo(methodOn(AchatController.class).un(achatDTO.id())).withSelfRel(),
                        linkTo(methodOn(AchatController.class).liste()).withRel("achats")
                );
    }
}
