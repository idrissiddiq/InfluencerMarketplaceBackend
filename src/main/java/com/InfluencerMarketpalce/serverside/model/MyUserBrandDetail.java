package com.InfluencerMarketpalce.serverside.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserBrandDetail implements UserDetails {
    private UserBrand userBrand;

    public MyUserBrandDetail() {
    }


    public MyUserBrandDetail(UserBrand userBrand) {
        this.userBrand = userBrand;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        userBrand.getRoles().forEach((role) -> {
            role.getPrivileges().forEach((privilege) -> {
                authorities.add(new SimpleGrantedAuthority(privilege.getName().toUpperCase()));
            });
        });

        userBrand.getRoles().forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
        });

        return authorities;
    }

    public String getId() {
        return userBrand.getPassword();
    }

    @Override
    public String getUsername() {
        return userBrand.getUsername();
    }

    @Override
    public String getPassword() {
        return userBrand.getPassword();
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
