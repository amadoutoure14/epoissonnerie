package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.AdministrateurModelAssembleur;
import com.source.epoissonnerie.controller.AdministrateurController;
import com.source.epoissonnerie.dto.AdministrateurDTO;
import com.source.epoissonnerie.entites.Administrateur;
import com.source.epoissonnerie.exceptions.AdministrateurIntrouvable;
import com.source.epoissonnerie.repositories.AdministrateurRepo;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class AdministrateurService {

    final public AdministrateurRepo administrateurRepo;
    final public BCryptPasswordEncoder encoder;
    private final AdministrateurModelAssembleur assembler;


    public EntityModel<Administrateur> un(Long id){

        Administrateur administrateur = administrateurRepo
                .findById(id)
                .orElseThrow(() -> new AdministrateurIntrouvable(id));

        return EntityModel.of(administrateur,
                linkTo(methodOn(AdministrateurController.class).un(id)).withSelfRel(),
                linkTo(methodOn(AdministrateurController.class).liste()).withRel("Administrateurs"));

    }
    public CollectionModel<EntityModel<Administrateur>> liste(){
        List<EntityModel<Administrateur>> Administrateurs = administrateurRepo
                .findAll()
                .stream()
                .map(administrateur -> EntityModel.of(administrateur,
                        linkTo(methodOn(AdministrateurController.class).un(administrateur.getId())).withSelfRel(),
                        linkTo(methodOn(AdministrateurController.class).liste()).withRel("Administrateurs")))
                .collect(Collectors.toList());

        return CollectionModel.of(Administrateurs, linkTo(methodOn(AdministrateurController.class).liste()).withSelfRel());
    }

    public ResponseEntity<?> nouveau(Administrateur administrateur) {
        String mdp = encoder.encode(administrateur.getMdp());
        administrateur.setMdp(mdp);
        EntityModel<AdministrateurDTO> entityModel = assembler.toModel(administrateurRepo.save(administrateur));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifier(Long id,Administrateur administrateur) {
        String mdp = encoder.encode(administrateur.getMdp());
        administrateur.setMdp(mdp);
        Administrateur optionalAdministrateur = administrateurRepo
                .findById(id)
                .map(
                        maj -> {
                            maj.setNom(administrateur.getNom());
                            maj.setEmail(administrateur.getEmail());
                            maj.setMdp(administrateur.getMdp());
                            maj.setTel(administrateur.getTel());
                            return administrateurRepo.save(maj);
                        })
                .orElseGet(
                        () -> {
                            administrateur.setId(id);
                            return administrateurRepo.save(administrateur);
                        });

        EntityModel<AdministrateurDTO> entityModel = assembler.toModel(optionalAdministrateur);

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifierPartiel(Long id, Map<String, Object> administrateur) {
        Administrateur AdministrateurOptional = administrateurRepo
                .findById(id)
                .orElseThrow(
                        () -> new AdministrateurIntrouvable(id));
        administrateur.forEach(
                (key, value) -> {
                    switch (key) {
                        case "nom":
                            AdministrateurOptional.setNom((String) value);
                            break;
                        case "tel":
                            AdministrateurOptional.setTel((Integer) value);
                            break;
                            case "email":
                                AdministrateurOptional.setEmail((String) value);
                            break;
                        case "mdp":
                            if(value instanceof String ){
                                String mdp = encoder.encode((String) value);
                                AdministrateurOptional.setMdp(mdp);
                            }
                            break;
                        default:
                            throw new AdministrateurIntrouvable( id);
                    }
                });

        EntityModel<AdministrateurDTO> entityModel = assembler.toModel(administrateurRepo.save(AdministrateurOptional));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    public ResponseEntity<?> supprimer(Long id) {
        administrateurRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
