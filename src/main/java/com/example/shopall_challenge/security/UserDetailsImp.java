package com.example.shopall_challenge.security;

import com.example.shopall_challenge.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserDetailsImp implements UserDetails {

    private final Usuario user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRol()))
                .collect(Collectors.toSet());

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getContrasena();
    }

    @Override
    public String getUsername() {
        return user.getNombre_usuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getEmail() {
        return user.getEmail();
    }


}
