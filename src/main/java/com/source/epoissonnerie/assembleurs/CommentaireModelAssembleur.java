package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.dto.CommentaireDTO;
import com.source.epoissonnerie.entites.Commentaire;
import com.source.epoissonnerie.exceptions.CommentaireIntrouvable;
import com.source.epoissonnerie.mapper.CommentaireMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class CommentaireModelAssembleur implements RepresentationModelAssembler<Commentaire, EntityModel<CommentaireDTO>> {
    final CommentaireMapper mapper;
    @Override
    public EntityModel<CommentaireDTO> toModel(Commentaire commentaire) {
        CommentaireDTO commentaireDTO = mapper.apply(commentaire);
        return EntityModel
                .of(
                        commentaireDTO,
                        linkTo(methodOn(CommentaireIntrouvable.class).un(commentaireDTO.id())).withSelfRel(),
                        linkTo(methodOn(CommentaireIntrouvable.class).tout()).withRel("commentaires")
                );
    }
}
