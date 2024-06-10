package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.CategorieController;
import com.source.epoissonnerie.dto.CategorieDTO;
import com.source.epoissonnerie.entites.Categorie;
import com.source.epoissonnerie.mapper.CategorieMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class CategorieModelAssembleur implements RepresentationModelAssembler<Categorie, EntityModel<CategorieDTO>> {
    final private CategorieMapper mapper;
    @Override
    public EntityModel<CategorieDTO> toModel(Categorie categorie) {
        CategorieDTO categorieDTO = mapper.apply(categorie);
        return EntityModel
                .of(
                        categorieDTO,
                linkTo(methodOn(CategorieController.class).une(categorieDTO.id())).withSelfRel(),
                        linkTo(methodOn(CategorieController.class).liste()).withRel("categories")
        );
    }
}
