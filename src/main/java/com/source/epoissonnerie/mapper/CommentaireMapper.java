package com.source.epoissonnerie.mapper;

import com.source.epoissonnerie.dto.CommentaireDTO;
import com.source.epoissonnerie.entites.Commentaire;

import java.util.function.Function;

public class CommentaireMapper implements Function<Commentaire, CommentaireDTO> {
    @Override
    public CommentaireDTO apply(Commentaire commentaire) {
        return new CommentaireDTO(commentaire.getId(),commentaire.getContenu(),commentaire.getDate());
    }
}
