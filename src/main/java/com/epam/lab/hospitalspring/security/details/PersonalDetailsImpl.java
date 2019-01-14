package com.epam.lab.hospitalspring.security.details;

import com.epam.lab.hospitalspring.model.Personal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class PersonalDetailsImpl implements UserDetails {
    private Personal personal;

    public PersonalDetailsImpl(Personal personal) {
        this.personal = personal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(personal.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return personal.getPassword();
    }

    @Override
    public String getUsername() {
        return personal.getLogin();
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

    public Personal getPersonal() {
        return personal;
    }
}
