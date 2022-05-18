/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketpalce.serverside.service;

import com.InfluencerMarketpalce.serverside.model.User;
import com.InfluencerMarketpalce.serverside.model.response.LoginRequestDto;
import com.InfluencerMarketpalce.serverside.model.response.LoginResponseDto;
import com.InfluencerMarketpalce.serverside.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class LoginService {

    private MyUserDetailService myUserDetailService;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;

    @Autowired
    public LoginService(MyUserDetailService myUserDetailService, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.myUserDetailService = myUserDetailService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public LoginResponseDto login(LoginRequestDto loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        
        User user = userRepository.findByUsername(loginRequest.getUsername());
        UserDetails userDetails = myUserDetailService.loadUserByUsername(loginRequest.getUsername());
        
        List<String> authorities = userDetails.getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());
        
        return new LoginResponseDto(user.getId(), authorities);
    }    
}
