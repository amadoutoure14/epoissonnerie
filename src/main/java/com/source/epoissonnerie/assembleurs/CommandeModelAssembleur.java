package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.CommandeController;
import com.source.epoissonnerie.dto.CommandeDTO;
import com.source.epoissonnerie.entites.Commande;
import com.source.epoissonnerie.entites.Status;
import com.source.epoissonnerie.mapper.CommandeMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class CommandeModelAssembleur implements RepresentationModelAssembler<Commande, EntityModel<CommandeDTO>> {
    final private CommandeMapper mapper;
    @Override
    public EntityModel<CommandeDTO> toModel(Commande commande) {
        CommandeDTO commandeDTO = mapper.apply(commande);
        EntityModel<CommandeDTO> commandes = EntityModel
                .of(
                        commandeDTO,
                        linkTo(methodOn(CommandeController.class).une(commandeDTO.id())).withSelfRel(),
                        linkTo(methodOn(CommandeController.class).liste()).withRel("commandes")
                );
        if (commande.getStatus()== Status.EN_COURS){
            commandes.add(linkTo(methodOn(CommandeController.class).annuler(commandeDTO.id())).withRel("annuler"));
            commandes.add(linkTo(methodOn(CommandeController.class).complete(commandeDTO.id())).withRel("complete"));
        }
        return commandes;
    }
}
