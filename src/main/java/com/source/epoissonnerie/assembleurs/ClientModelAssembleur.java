package com.source.epoissonnerie.assembleurs;

import com.source.epoissonnerie.controller.ClientController;
import com.source.epoissonnerie.dto.ClientDTO;
import com.source.epoissonnerie.entites.Client;
import com.source.epoissonnerie.mapper.ClientMapper;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class ClientModelAssembleur implements RepresentationModelAssembler<Client, EntityModel<ClientDTO>> {
   final private ClientMapper mapper;
    @Override
    public EntityModel<ClientDTO> toModel(Client client) {
        ClientDTO clientDTO = mapper.apply(client);
        return EntityModel
                .of(
                        clientDTO,
                        linkTo(methodOn(ClientController.class).un(clientDTO.id())).withSelfRel(),
                        linkTo(methodOn(ClientController.class).liste()).withRel("clients")
                );
    }
}
