package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.VendeurModelAssembleur;
import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Vendeur;
import com.source.epoissonnerie.exceptions.CategorieIntrouvable;
import com.source.epoissonnerie.exceptions.VendeurIntrouvable;
import com.source.epoissonnerie.repositories.VendeurRepo;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class PoissonService {

    final public VendeurRepo vendeurRepository;
    final public BCryptPasswordEncoder encoder;
    private final VendeurModelAssembleur assembler;


    public EntityModel<Vendeur> un(Long id){

        Vendeur vendeur = vendeurRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Vendeur non trouvé!"));

        return EntityModel.of(vendeur,
                linkTo(methodOn(VendeurController.class).un(id)).withSelfRel(),
                linkTo(methodOn(VendeurController.class).tout()).withRel("vendeurs"));

    }
    public CollectionModel<EntityModel<Vendeur>> tout(){
        List<EntityModel<Vendeur>> vendeurs = vendeurRepository
                .findAll()
                .stream()
                .map(vendeur -> EntityModel.of(vendeur,
                        linkTo(methodOn(VendeurController.class).un(vendeur.getId())).withSelfRel(),
                        linkTo(methodOn(VendeurController.class).tout()).withRel("vendeurs")))
                .collect(Collectors.toList());

        return CollectionModel.of(vendeurs, linkTo(methodOn(VendeurController.class).tout()).withSelfRel());
    }

    public ResponseEntity<?> nouveauVendeur(Vendeur vendeur) {
        String mdp = encoder.encode(vendeur.getMdp());
        EntityModel<Vendeur> entityModel = assembler.toModel(vendeurRepository.save(vendeur));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifier(Vendeur vendeur,Long id) {
        String mdp = encoder.encode(vendeur.getMdp());
        Vendeur optionalVendeur = vendeurRepository
                .findById(id)
                .map(
                        maj -> {
                            maj.setNom(vendeur.getNom());
                            maj.setMdp(vendeur.getMdp());
                            maj.setAdresse(vendeur.getAdresse());
                            maj.setTel(vendeur.getTel());
                            return vendeurRepository.save(maj);
                        })
                .orElseGet(
                        () -> {
                            vendeur.setId(id);
                            return vendeurRepository.save(vendeur);
                        });
        EntityModel<Vendeur> entityModel = assembler.toModel(optionalVendeur);

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifierPartiel(Long id, Map<String, Object> vendeur) {
        Vendeur vendeurOptional = vendeurRepository
                .findById(id)
                .orElseThrow(() -> new VendeurIntrouvable(id));
        vendeur.forEach(
                (key, value) -> {
                    switch (key) {
                        case "nom":
                            vendeurOptional.setNom((String) value);
                            break;
                        case "tel":
                            vendeurOptional.setTel((Integer) value);
                            break;
                        case "mdp":
                            if(value instanceof String ){
                                String mdp = encoder.encode((String) value);
                                vendeurOptional.setMdp(mdp);
                            }
                            break;
                        case "adresse":
                            vendeurOptional.setAdresse((String) value);
                            break;
                        default:
                            throw new CategorieIntrouvable( id);
                    }
                });

        EntityModel<Vendeur> entityModel = assembler.toModel( vendeurRepository.save(vendeurOptional));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    public ResponseEntity<?> supprimer(Long id) {
        vendeurRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
