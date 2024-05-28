package com.source.epoissonnerie.config;

import com.source.epoissonnerie.entites.Utilisateur;
import com.source.epoissonnerie.services.UtilisateurService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
@AllArgsConstructor
@Service
public class JwtService {
    private UtilisateurService utilisateurService;

    public Map<String,String> generate(String username){
        Utilisateur utilisateur = this.utilisateurService.loadUserByUsername(username);
        return this.generateJwt(utilisateur);
    }

    private Map<String, String> generateJwt(Utilisateur utilisateur) {
        Map<String, String> claims = Map.of(
                "nom_complet",utilisateur.getNom_complet(),
                "email", utilisateur.getEmail()
        );
        final long current = System.currentTimeMillis();
        final long expiration = current + 30 * 60 * 1000;
        final String bearer = Jwts.builder()
                .setIssuedAt(new Date(current))
                .setExpiration(new Date(expiration))
                .setSubject(utilisateur.getEmail())
                .setClaims(claims)
                .signWith(getKey(), (SignatureAlgorithm.HS256))
                .compact();
        return Map.of("bearer",bearer);
    }

    private Key getKey() {
        String key = "a1610104482fda8bca832549f746feb918d5f0441d92adb9497dc7b6b411fc91";
        final byte[] decode = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(decode);
    }
}
