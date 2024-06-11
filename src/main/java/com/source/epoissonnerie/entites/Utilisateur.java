package com.source.epoissonnerie.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "Utilisateur")
@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nom;

    @Email
    @Column(unique = true)
    private String email;

    @Column(unique = true,length = 8)
    private int tel;

    @NotNull
    private String mdp;

    private boolean actif = true;

    @OneToOne(cascade = CascadeType.ALL)
    private final Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        assert this.role != null;
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role.getDescription()));
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
