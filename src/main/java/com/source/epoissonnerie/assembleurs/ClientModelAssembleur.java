package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.ClientController;
import com.source.epoissonnerie.entites.Client;
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
                        linkTo(methodOn(ClientController.class).un(client.getId())).withSelfRel(),
                        linkTo(methodOn(ClientController.class).liste()).withRel("clients")
                );
    }
}
