/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.model.response;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class LoginResponseDto {
    private Long id;
    private List<String> authorities;

    public LoginResponseDto() {
    }

    public LoginResponseDto(Long id, List<String> authorities) {
        this.id = id;
        this.authorities = authorities;
    }

    
    public Long getId() {
        return id;
    }


    public List<String> getAuthorities() {
        return authorities;
    }
    
}
