package com.source.epoissonnerie.services;

import com.source.epoissonnerie.assembleurs.ClientModelAssembleur;
import com.source.epoissonnerie.controller.ClientController;
import com.source.epoissonnerie.entites.Client;
import com.source.epoissonnerie.exceptions.CategorieIntrouvable;
import com.source.epoissonnerie.exceptions.ClientIntrouvable;
import com.source.epoissonnerie.repositories.ClientRepo;
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
public class ClientService {

    final public ClientRepo clientRepo;
    final public BCryptPasswordEncoder encoder;
    private final ClientModelAssembleur assembler;


    public EntityModel<Client> un(Long id){

        Client client = clientRepo
                .findById(id)
                .orElseThrow(() -> new ClientIntrouvable(id));

        return EntityModel.of(client,
                linkTo(methodOn(ClientController.class).un(id)).withSelfRel(),
                linkTo(methodOn(ClientController.class).liste()).withRel("clients"));

    }
    public CollectionModel<EntityModel<Client>> liste(){
        List<EntityModel<Client>> clientsList = clientRepo
                .findAll()
                .stream()
                .map(client -> EntityModel.of(
                        client,
                        linkTo(methodOn(ClientController.class).un(client.getId())).withSelfRel(),
                        linkTo(methodOn(ClientController.class).liste()).withRel("clientsList")))
                .collect(Collectors.toList());

        return CollectionModel.of(clientsList, linkTo(methodOn(ClientController.class).liste()).withSelfRel());
    }

    public ResponseEntity<?> nouveauClient(Client client) {
        String mdp = encoder.encode(client.getMdp());
        client.setMdp(mdp);
        EntityModel<Client> entityModel = assembler.toModel(
                clientRepo.save(client)
        );
        return ResponseEntity
                .created(entityModel.getRequiredLink(
                        IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifier(Long id,Client client) {
        String mdp = encoder.encode(client.getMdp());
        client.setMdp(mdp);
        Client optionalClient = clientRepo
                .findById(id)
                .map(
                        maj -> {
                            maj.setMdp(client.getMdp());
                            maj.setMdp(client.getMdp());
                            maj.setAdresse(client.getAdresse());
                            maj.setTel(client.getTel());
                            return clientRepo.save(maj);
                        })
                .orElseGet(
                        () -> {
                            client.setId(id);
                            return clientRepo.save(client);
                        });
        EntityModel<Client> entityModel = assembler.toModel(optionalClient);

        return ResponseEntity
                .created(
                        entityModel
                                .getRequiredLink(IanaLinkRelations.SELF)
                                .toUri())
                .body(entityModel);
    }

    public ResponseEntity<?> modifierPartiel(Long id, Map<String, Object> client) {

        Client ClientOptional = clientRepo
                .findById(id)
                .orElseThrow(() -> new ClientIntrouvable(id));
        client.forEach(
                (key, value) -> {
                    switch (key) {
                        case "nom":
                            ClientOptional.setNom((String) value);
                            break;
                        case "tel":
                            ClientOptional.setTel((Integer) value);
                            break;
                        case "mdp":
                            if(value instanceof String ){
                                String mdp = encoder.encode((String) value);
                                ClientOptional.setMdp(mdp);
                            }
                            break;
                        case "adresse":
                            ClientOptional.setAdresse((String) value);
                            break;
                        default:
                            throw new CategorieIntrouvable( id);
                    }
                });

        EntityModel<Client> entityModel = assembler.toModel( clientRepo.save(ClientOptional));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    public ResponseEntity<?> supprimer(Long id) {
        clientRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
