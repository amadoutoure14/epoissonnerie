package com.source.epoissonnerie.assembleurs;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.dto.VendeurDTO;
import com.source.epoissonnerie.entites.Vendeur;
import com.source.epoissonnerie.mapper.VendeurMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VendeurModelAssembleur implements RepresentationModelAssembler<Vendeur, EntityModel<VendeurDTO>> {
    private final VendeurMapper vendeurMapper;
    @Override
    public EntityModel<VendeurDTO> toModel(Vendeur vendeur) {
        VendeurDTO vendeurDTO = vendeurMapper.apply(vendeur);
        return EntityModel
                .of(
                        vendeurDTO,
                linkTo(methodOn(VendeurController.class).un(vendeurDTO.id())).withSelfRel(),
                linkTo(methodOn(VendeurController.class).liste()).withRel("vendeurs")
                );
    }
}