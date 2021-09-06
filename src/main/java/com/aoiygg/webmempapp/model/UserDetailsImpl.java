package com.aoiygg.webmempapp.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final MyNotepadsUser user;
    private final Collection<GrantedAuthority> authorities;

    public UserDetailsImpl(MyNotepadsUser user) {
        this.user = user;
        this.authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
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

    @Override
    public String toString() {
        return user.toString();
    }
}
