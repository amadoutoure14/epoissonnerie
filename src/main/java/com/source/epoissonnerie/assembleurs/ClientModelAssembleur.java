package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.VendeurController;
import com.source.epoissonnerie.entites.Client;
import com.source.epoissonnerie.entites.Vendeur;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClientModelAssembleur implements RepresentationModelAssembler<Client, EntityModel<Client>> {
    @Override
    public EntityModel<Client> toModel(Client client) {
        return EntityModel
                .of(
                        client,
                        linkTo(methodOn(VendeurController.class).un(client.getId())).withSelfRel(),
                        linkTo(methodOn(VendeurController.class).tout()).withRel("clients")
                );
    }
}
