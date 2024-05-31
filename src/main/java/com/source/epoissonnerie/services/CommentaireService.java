package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.CommentaireModelAssembleur;
import com.source.epoissonnerie.controller.CommentaireController;
import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Commentaire;
import com.source.epoissonnerie.exceptions.CommentaireIntrouvable;
import com.source.epoissonnerie.repositories.CommentaireRepo;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class CommentaireService {

    final public CommentaireRepo commentaireRepo;
    final private CommentaireModelAssembleur assembler;


    public EntityModel<Commentaire> un(Long id){

        Commentaire commentaire = commentaireRepo
                .findById(id)
                .orElseThrow(
                        () -> new CommentaireIntrouvable(id));

        return EntityModel.of(
                commentaire,
                linkTo(methodOn(CommentaireController.class).un(id)).withSelfRel(),
                linkTo(methodOn(CommentaireController.class).liste()).withRel("commentaires"));

    }
    public CollectionModel<EntityModel<Commentaire>> liste(){
        List<EntityModel<Commentaire>> entityModelList = commentaireRepo
                .findAll()
                .stream()
                .map(
                        commentaire -> EntityModel.of(
                                commentaire,
                        linkTo(methodOn(CommentaireController.class).un(commentaire.getId())).withSelfRel(),
                        linkTo(methodOn(CommentaireController.class).liste()).withRel("commentaires")))
                .collect(Collectors.toList());

        return CollectionModel.of(entityModelList, linkTo(methodOn(VendeurController.class).liste()).withSelfRel());
    }

    public ResponseEntity<?> nouveau(Commentaire commentaire) {
        EntityModel<Commentaire> entityModel = assembler
                .toModel(commentaireRepo.save(commentaire));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifier(Long id,Commentaire commentaire) {
        Commentaire optionalCommentaire = commentaireRepo
                .findById(id)
                .map(
                        maj -> {
                            maj.setContenu(commentaire.getContenu());
                            maj.setDate(commentaire.getDate());
                            return commentaireRepo.save(maj);
                        })
                .orElseGet(
                        () -> {
                            commentaire.setId(id);
                            return commentaireRepo.save(commentaire);
                        });
        EntityModel<Commentaire> entityModel = assembler.toModel(optionalCommentaire);

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }


    public ResponseEntity<?> supprimer(Long id) {
        commentaireRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
