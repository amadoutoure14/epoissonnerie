package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.UtilisateurController;
import com.source.epoissonnerie.dto.UtilisateurDTO;
import com.source.epoissonnerie.entites.Utilisateur;
import com.source.epoissonnerie.mapper.UtilisateurMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
@AllArgsConstructor
public class UtilisateurModelAssembleur implements RepresentationModelAssembler<Utilisateur, EntityModel<UtilisateurDTO>> {
    final private UtilisateurMapper mapper;
    @Override
    public EntityModel<UtilisateurDTO> toModel(Utilisateur utilisateur) {
        UtilisateurDTO utilisateurDTO = mapper.apply(utilisateur);
        return EntityModel
                .of(
                        utilisateurDTO,
                        linkTo(methodOn(UtilisateurController.class).un(utilisateurDTO.id())).withSelfRel(),
                        linkTo(methodOn(UtilisateurController.class).liste()).withRel("utilisateurs")
                );
    }
}
