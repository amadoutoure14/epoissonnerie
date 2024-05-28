package com.source.epoissonnerie.services;

import com.source.epoissonnerie.Assembleurs.VendeurModelAssembleur;
import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.VendeurPoisson;
import com.source.epoissonnerie.repository.VendeurRepository;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Service
@AllArgsConstructor
public class VendeurService {

    final public VendeurRepository vendeurRepository;
    final public BCryptPasswordEncoder encoder;
    private final VendeurModelAssembleur assembler;


    public EntityModel<VendeurPoisson> un(int id){

        VendeurPoisson vendeur = vendeurRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Vendeur non trouvé!"));

        return EntityModel.of(vendeur,
                linkTo(methodOn(VendeurController.class).un(id)).withSelfRel(),
                linkTo(methodOn(VendeurController.class).tout()).withRel("vendeurs"));

    }
    public CollectionModel<EntityModel<VendeurPoisson>> tout(){
        List<EntityModel<VendeurPoisson>> employees = vendeurRepository.findAll().stream()
                .map(vendeur -> EntityModel.of(vendeur,
                        linkTo(methodOn(VendeurController.class).un(vendeur.getId())).withSelfRel(),
                        linkTo(methodOn(VendeurController.class).tout()).withRel("vendeurs")))
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(VendeurController.class).tout()).withSelfRel());
    }

    public ResponseEntity<?> nouveauVendeur(VendeurPoisson vendeur) {
        EntityModel<VendeurPoisson> entityModel = assembler.toModel(vendeurRepository.save(vendeur));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
