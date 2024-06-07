package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.ClientDTO;
import com.source.epoissonnerie.entites.Client;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClientMapper implements Function<Client, ClientDTO> {
    @Override
    public ClientDTO apply(Client client) {
        return new ClientDTO(client.getId(),client.getNom(),client.getMdp(),client.getAdresse(),client.getTel());
    }
}
