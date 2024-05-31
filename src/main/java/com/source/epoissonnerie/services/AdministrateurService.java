package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.AdministrateurModelAssembleur;
import com.source.epoissonnerie.controller.AdministrateurController;
import com.source.epoissonnerie.entites.Administrateur;
import com.source.epoissonnerie.exceptions.AdministrateurIntrouvable;
import com.source.epoissonnerie.exceptions.CategorieIntrouvable;
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

    final public AdministrateurRepo AdministrateurRepository;
    final public BCryptPasswordEncoder encoder;
    private final AdministrateurModelAssembleur assembler;


    public EntityModel<Administrateur> un(Long id){

        Administrateur administrateur = AdministrateurRepository
                .findById(id)
                .orElseThrow(() -> new AdministrateurIntrouvable(id));

        return EntityModel.of(administrateur,
                linkTo(methodOn(AdministrateurController.class).un(id)).withSelfRel(),
                linkTo(methodOn(AdministrateurController.class).tout()).withRel("Administrateurs"));

    }
    public CollectionModel<EntityModel<Administrateur>> tout(){
        List<EntityModel<Administrateur>> Administrateurs = AdministrateurRepository
                .findAll()
                .stream()
                .map(administrateur -> EntityModel.of(administrateur,
                        linkTo(methodOn(AdministrateurController.class).un(administrateur.getId())).withSelfRel(),
                        linkTo(methodOn(AdministrateurController.class).tout()).withRel("Administrateurs")))
                .collect(Collectors.toList());

        return CollectionModel.of(Administrateurs, linkTo(methodOn(AdministrateurController.class).tout()).withSelfRel());
    }

    public ResponseEntity<?> nouveauAdministrateur(Administrateur administrateur) {
        String mdp = encoder.encode(administrateur.getMdp());
        EntityModel<Administrateur> entityModel = assembler.toModel(AdministrateurRepository.save(administrateur));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifier(Administrateur administrateur,Long id) {
        String mdp = encoder.encode(administrateur.getMdp());
        Administrateur optionalAdministrateur = AdministrateurRepository
                .findById(id)
                .map(
                        maj -> {
                            maj.setNom(administrateur.getNom());
                            maj.setMdp(administrateur.getMdp());
                            maj.setTel(administrateur.getTel());
                            return AdministrateurRepository.save(maj);
                        })
                .orElseGet(
                        () -> {
                            administrateur.setId(id);
                            return AdministrateurRepository.save(administrateur);
                        });
        EntityModel<Administrateur> entityModel = assembler.toModel(administrateur);

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifierPartiel(Long id, Map<String, Object> Administrateur) {
        Administrateur AdministrateurOptional = AdministrateurRepository
                .findById(id)
                .orElseThrow(() -> new AdministrateurIntrouvable(id));
        Administrateur.forEach(
                (key, value) -> {
                    switch (key) {
                        case "nom":
                            AdministrateurOptional.setNom((String) value);
                            break;
                        case "tel":
                            AdministrateurOptional.setTel((Integer) value);
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

        EntityModel<Administrateur> entityModel = assembler.toModel(AdministrateurRepository.save(AdministrateurOptional));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    public ResponseEntity<?> supprimer(Long id) {
        AdministrateurRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
