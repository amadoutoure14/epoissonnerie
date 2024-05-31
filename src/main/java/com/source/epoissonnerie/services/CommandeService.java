package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.CommandeModelAssembleur;
import com.source.epoissonnerie.controller.CommandeController;
import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Commande;
import com.source.epoissonnerie.entites.Status;
import com.source.epoissonnerie.exceptions.CommandeIntrouvable;
import com.source.epoissonnerie.repositories.CommandeRepo;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class CommandeService {

    final public CommandeRepo commandeRepo;
    private final CommandeModelAssembleur assembler;


    public EntityModel<Commande> une(Long id){

        Commande commande = commandeRepo
                .findById(id)
                .orElseThrow(
                        () -> new CommandeIntrouvable(id));

        return EntityModel.of(commande,
                linkTo(methodOn(CommandeController.class).une(id)).withSelfRel(),
                linkTo(methodOn(CommandeController.class).liste()).withRel("commandes"));

    }
    public CollectionModel<EntityModel<Commande>> liste(){
        List<EntityModel<Commande>> entityModelList = commandeRepo
                .findAll()
                .stream()
                .map(
                        commande -> EntityModel.of(
                                commande,
                        linkTo(methodOn(CommandeController.class).une(commande.getId())).withSelfRel(),
                        linkTo(methodOn(CommandeController.class).liste()).withRel("commandes")))
                .collect(Collectors.toList());

        return CollectionModel.of(entityModelList, linkTo(methodOn(VendeurController.class).liste()).withSelfRel());
    }

    public ResponseEntity<?> nouvelle(Commande commande) {
        EntityModel<Commande> entityModel = assembler.toModel(commandeRepo.save(commande));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifier(Long id,Commande commande) {
        Commande optionalCommande = commandeRepo
                .findById(id)
                .map(
                        maj -> {
                            maj.setDescription(commande.getDescription());
                            maj.setStatus(commande.getStatus());
                            return commandeRepo.save(maj);
                        })
                .orElseGet(
                        () -> {
                            commande.setId(id);
                            return commandeRepo.save(commande);
                        });
        EntityModel<Commande> entityModel = assembler.toModel(optionalCommande);

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifierPartiel(Long id, Map<String, Object> commande) {
        Commande commandeOptional = commandeRepo
                .findById(id)
                .orElseThrow(
                        () -> new CommandeIntrouvable(id));
        commande.forEach(
                (key, value) -> {
                    switch (key) {
                        case "statut":
                            commandeOptional.setStatus(Status.valueOf((String) value));
                            break;
                        case "description":
                            commandeOptional.setDescription((String) value);
                            break;
                        default:
                            throw new CommandeIntrouvable( id);
                    }
                });

        EntityModel<Commande> entityModel = assembler.toModel( commandeRepo.save(commandeOptional));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    public ResponseEntity<?> supprimer(Long id) {
        commandeRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
