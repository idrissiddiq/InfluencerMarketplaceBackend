package com.InfluencerMarketpalce.serverside.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUserBrandDetail implements UserDetails {
    private UserBrand userBrand;

    public AppUserBrandDetail(UserBrand userBrand) {
        this.userBrand = userBrand;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        userBrand.getRoles().forEach((x) -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + x.getName().toUpperCase()));
        });

        userBrand.getRoles().forEach((x) -> {
            x.getPrivileges().forEach((y) -> {
                authorities.add(new SimpleGrantedAuthority(y.getName().toUpperCase()));
            });
        });

        System.out.println("AUTHORITIES : " +authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return userBrand.getPassword();
    }

    @Override
    public String getUsername() {
        return userBrand.getUsername();
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
