package com.InfluencerMarketpalce.serverside.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MyUserAdminDetail implements UserDetails {
    private UserAdmin userAdmin;

    public MyUserAdminDetail() {
    }


    public MyUserAdminDetail(UserAdmin userAdmin) {
        this.userAdmin = userAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        userAdmin.getRoles().forEach((role) -> {
            role.getPrivileges().forEach((privilege) -> {
                authorities.add(new SimpleGrantedAuthority(privilege.getName().toUpperCase()));
            });
        });

        userAdmin.getRoles().forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
        });

        return authorities;
    }

    public String getId() {
        return userAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return userAdmin.getUsername();
    }

    @Override
    public String getPassword() {
        return userAdmin.getPassword();
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
}
