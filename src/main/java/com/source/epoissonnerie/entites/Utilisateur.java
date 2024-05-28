package com.source.epoissonnerie.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "Utilisateur")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nom_complet;

    @Email
    @Column(unique = true)
    private String email;

    @Column(unique = true,length = 8)
    private int tel;

    @NotNull
    @Size(min = 4)
    private String mdp;

    private boolean actif = false;

    private LocalDate date;

    @OneToOne(cascade = CascadeType.ALL)
    private final Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+this.role.getLibelle()));
    }

    @Override
    public String getPassword() {
        return this.mdp;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return  this.actif;
    }

    @Override
    public boolean isAccountNonLocked() {
        return  this.actif;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return  this.actif;
    }

    @Override
    public boolean isEnabled() {
        return this.actif;
    }
}
